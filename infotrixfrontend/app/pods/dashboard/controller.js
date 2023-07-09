import Controller from '@ember/controller';

export default Controller.extend({
    actions:{
        redirect_to_login()
        {
            this.transitionToRoute("login");
        },
        redirect_to_signup()
        {
            this.transitionToRoute("signup");
        }
    }
});
