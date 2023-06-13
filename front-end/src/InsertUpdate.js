import React, { Component } from 'react';
import { UserContext } from './UserContext';

//Insert////////////////////////////////////////////
export class Insert extends Component {        
    
    static contextType = UserContext;

    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password:" ",
            email: " ",
            requestsPerDayQuota: " "

        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        //console.log("handle change", event);

        this.setState({
            [event.target.name]: event.target.value

        })
    }


    handleSubmit(event){
        //console.log("form submitted");

                const username= this.state.username;
                const password= this.state.password;
                const email= this.state.email;
                const requestsPerDayQuota= this.state.requestsPerDayQuota;
      

        fetch(`https://localhost:8765/energy/api/Admin/users`, {
        method: 'POST'
        , 
        headers: {
            'X-OBSERVATORY-AUTH': this.context.token,
            'Content-Type':'application/x-www-form-urlencoded',
        },
          body: new URLSearchParams ({
            "username": username,
            "password": password,
            "email": email,
            "requestsPerDayQuota":requestsPerDayQuota
          })

    }).then(response => {
            console.log("res", response);

        })
        .catch(error => {
            console.log("error", error);
        });


        event.preventDefault();
    }
    render() {        
        return (                                    
            <form onSubmit={this.handleSubmit}>
                <li>
                <input type="text" name="username" placeholder="Username" value={this.state.username} onChange={this.handleChange} required />
                </li>

                <li>
                <input type="password" name="password" placeholder="Password" value={this.state.passsword} onChange={this.handleChange} required />
                </li>


                <li>
                <input type="email" name="email" placeholder="Email" value={this.state.email} onChange={this.handleChange} required />
                </li>

                <li>
                <input type="int" name="requestsPerDayQuota" placeholder="RequestsPerDayQuota" value={this.state.requestsPerDayQuota} onChange={this.handleChange} required />
                </li>

                <button className="btn btn-primary" type="submit">
                    Insert
                </button>
            </form>
        );        
    }    
}

//Update /////////////////////////////////////////////////////////////////////////
export class Update extends Component { 
    static contextType = UserContext;
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            email: " ",
            requestsPerDayQuota: " "
        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }
    handleChange(event){
        //console.log("handle change", event);
        this.setState({
            [event.target.name]: event.target.value

        })
    }

    handleSubmit(event){
        //console.log("form submitted");

                const username= this.state.username;
                const email= this.state.email;
                const requestsPerDayQuota= this.state.requestsPerDayQuota;
      

        fetch(`https://localhost:8765/energy/api/Admin/users/${username}`, {
        method: 'PUT'
        , 
        headers: {
            'X-OBSERVATORY-AUTH': this.context.token,
            'Content-Type':'application/x-www-form-urlencoded',
        },
          body: new URLSearchParams ({
            "email": email,
            "requestsPerDayQuota":requestsPerDayQuota
          })

    }).then(response => {
            console.log("res", response);
        })
        .catch(error => {
            console.log("error", error);
        });


        event.preventDefault();
    }
    

    render(){
        return(
            <form onSubmit={this.handleSubmit} >
     
                <li>
                <input type="text" name="username" placeholder="Username" value={this.state.username} onChange={this.handleChange} required />
                </li>
                <li>
                <input type="email" name="email" placeholder="Email" value={this.state.email} onChange={this.handleChange} required />
                </li>
                <li>
                <input type="int" name="requestsPerDayQuota" placeholder="RequestsPerDayQuota" value={this.state.requestsPerDayQuota} onChange={this.handleChange} required />
                </li>
                <button className="btn btn-primary" type="submit">
                    Update User
                </button>
            </form>
        );
    }
}
//State of User///////////////////////////////////
export class State extends Component {        
    
    static contextType = UserContext;

    constructor(props) {
        super(props);

        this.state = {
            username: "",
           
            items:[],
            isloaded: false,

        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        //console.log("handle change", event);

        this.setState({
            [event.target.name]: event.target.value

        })
    }


    handleSubmit(event){
        //console.log("form submitted");

                const username= this.state.username;
                //const password= this.state.password;
                //const email= this.state.email;
                //const requestsPerDayQuota= this.state.requestsPerDayQuota;
      

        fetch(`https://localhost:8765/energy/api/Admin/users/${username}`, {
        method: 'GET'
        , 
        headers: {
            'X-OBSERVATORY-AUTH': this.context.token,
            'Content-Type':'application/x-www-form-urlencoded',
        }

    }).then(response => response.json())
        .then(json => { 
            this.setState({
                items: json,
                isloaded: true,

            });
        })
        .catch(error => {
            console.log("error", error);
        });


        event.preventDefault();
    }
/*
        axios.post(`https://localhost:8765/energy/api/Admin/users`, 
            {user},
            { 'X-OBSERVATORY-AUTH': this.context.token }

        ).then(response => {
            console.log("res", response);
        })
        .catch(error => {
            console.log("error", error);
        });


        event.preventDefault();
    }

*/

renderIf(){
      
    if(!this.state.isloaded ){
         return <div> Is loading....</div>
     }

    else {
         return(
     <div className= "State">
                     <ul>
                         {this.state.items.map(item =>(

                         <li>
                           Username: {item.username} | Email: {item.email} | Current Quota: {item.requestsPerDayQuota} 
                         </li>
                         ))};
                     </ul>
                 </div>
         );

}
}
    render() {        
        return (   
            <div>                            
            <form onSubmit={this.handleSubmit}>
                <li>
                <input type="text" name="username" placeholder="Username" value={this.state.username} onChange={this.handleChange} required />
                </li>

               
                <button className="btn btn-primary" type="submit">
                    See the user state
                </button>
            </form>
            {this.renderIf()}

            </div>     
        );        
    }    
}


