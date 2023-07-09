import Controller from '@ember/controller';
export default Controller.extend({
    actions:{
        check(){
            Ember.$.ajax({
                url:'http://localhost:8080/infotrixbackend/login',
                type:'POST'
                
            }).then((response)=>{

                console.log(response);
            });
        },
        redirect_to_signup(){
            this.transitionToRoute("signup");
        },
        login(){
            console.log("hello");
            let mail = document.getElementById("email").value;
            let password= document.getElementById("password").value;
            Ember.$.ajax({
                url:'http://localhost:8080/infotrixbackend/login',
                type:'POST',
                dataType: 'json',
                data:
                {
                      mail:mail,
                      pass:password
                }
            }).then ((response) => {
                
                let data=response.success;
                let id = response.userid;
                console.log(data);
                console.log(id);
                if(data == "login failed incorrect username or password")
                {
                    alert("incorrect email or password");
                }
                else{
                    this.transitionToRoute('prerequisites');
                
                }
                
            });
        }
    }

});
