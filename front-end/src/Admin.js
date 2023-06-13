import React, { Component } from 'react';
//import { HashRouter as Router, Route, Redirect } from "react-router-dom";
//import {Actual} from './Actual';
import { BrowserRouter, Route, Switch } from 'react-router-dom';



import NavAdmin from './NavAdmin';
import {Insert, Update, State} from './InsertUpdate';


export class Admin extends Component {



render() {
    return (      
       <BrowserRouter>
        <div>
          <NavAdmin />
            <Switch>
             <Route path="/InsertUser" component={Insert}/>
             <Route path="/UpdateUser" component={Update}/>
            <Route path="/UserState" component={State}/>
           </Switch>
        </div> 
      </BrowserRouter>
    );
  }
}
  export default Admin;