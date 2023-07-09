import Controller from '@ember/controller';

export default Controller.extend({
    actions:{
        redirecttologin()
        {
            this.transitionToRoute("login");
        },
        saveDetails(){
            let check=true;
            let name=document.getElementById("registerName").value;
            let email = document.getElementById("registerEmail").value;
            let password = document.getElementById("registerPassword").value;
            let repeatpassword = document.getElementById("registerRepeatPassword").value;
            if(name.length < 5)
            {
                alert("pls enter a name having minimum five character");
                check=false;
            }
            if(password.length<8)
            {
                alert("pls enter a password greater than 8.");
                check=false;
            }
            if(password!==repeatpassword)
            {
               alert("repeat password is not matching");
               check=false;
            }
            if(check){
             Ember.$.ajax({
                 url:'http://localhost:8080/infotrixbackend/signup',
                 type:'POST',
                 dataType: 'json',
                 data:
                 {
                       name:name,
                       email:email,
                       password:password
                 }
                 }).then ((response) => {
                 
                     let data=response.success;
                     console.log(data);
                     if(data=="user already exists")
                     {
                         alert("user exits pls log in");
                     }
                     else{
                         alert("registered successfully");
                         this.transitionToRoute('login');
                     }
                 });
            }
            
         }
    }
});
