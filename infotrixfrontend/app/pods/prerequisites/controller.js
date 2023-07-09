import Controller from '@ember/controller';

export default Controller.extend({
    init(){
        this.add=true,
        this.listid=null,
        this.taskid=null,
        this.email=null
    },
    actions:
    {
        edit(value,id){
            console.log(value);
            this.taskid = id;
            document.getElementById("todolist").value= value;
            this.set('add',false);
        },
        change(){
            var value = document.getElementById("todolist").value;
            console.log(value);
            console.log(this.taskid);
            document.getElementById("todolist").value= "";
            this.set('add',true);
            this.send("updateTask",value);

        },
        addTask()
        {
            var userid=1;
            var note=document.getElementById("todolist").value;
            if(note.length==0)
            {
                alert("enter a valid task");
                return;
            }
            Ember.$.ajax({
                url:'http://localhost:8080/infotrixbackend/AddTask',
                type:'POST',
                dataType: 'json',
                data:
                {
                      id:userid,
                      note:note
                }
                }).then ((response) => {
                
                    let data=response.success;
                    console.log(data);
                    if(data=="task added")
                    {
                        alert("task added");
                        document.getElementById("todolist").value="";
                        this.send("getTasks");
                    }
                    else{
                        console.log("not added");
                    }
                });
        },
        deleteTask(tid)
        {
            Ember.$.ajax({
                url:'http://localhost:8080/infotrixbackend/deleteTask',
                type:'POST',
                dataType: 'json',
                data:
                {
                      id:tid
                }
                }).then ((response) => {
                    if(response.response == "deleted")
                    {
                        alert("task deleted");
                        this.send("getTasks");
                    }
                    else
                    {
                        console.log("not deleted");
                    }
                });
        },
        updateTask(msg){
            var taskid=this.taskid;
            Ember.$.ajax({
                url:'http://localhost:8080/infotrixbackend/updateTask',
                type:'POST',
                dataType: 'json',
                data:
                {
                      id:taskid,
                      note:msg
                }
                }).then ((response) => {
                    if(response.response == "updated")
                    {
                        alert("task updated");
                        this.send("getTasks");
                    }
                    else
                    {
                        console.log("not updated");
                    }
                });
        },
        getTasks()
        {
            var userid=1;
            Ember.$.ajax({
                url:'http://localhost:8080/infotrixbackend/getTasks',
                type:'POST',
                dataType: 'json',
                data:
                {
                      id:userid,
                }
                }).then ((response) => {
                
                    this.set("results",response.result);
                    console.log(response.Email);
                    this.set("email",response.Email);
                });
        },
        logout()
        {
            this.transitionToRoute('dashboard');
        }

    }
});
