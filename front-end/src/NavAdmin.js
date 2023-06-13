/*import React, { Component } from 'react';

import { Link, withRouter} from "react-router-dom";

const NavLink = props => {    
    const link = <Link className="nav-link" to={props.to}>{props.label}</Link>;
    if (props.to === props.location) {
        return <li className="nav-item active">{link}</li>
    }
    else {
        return <li className="nav-item">{link}</li>
    }    
}


//const Navigation = () => {
    class NavAdminMenu extends Component {
        render(){
    return (
       <div>
         <ul>
          <li>
          <NavLink label="Insert User" to="/InsertUser" location={this.props.location.pathname} />
          </li>
          <li>
          <NavLink label="Update User" to= "/UpdateUser" location={this.props.location.pathname}/>
          </li>
          <li>
          <NavLink label="User State" to="/UserState" location={this.props.location.pathname} />           
          </li>
         </ul>
       </div>
    );
}
    }

    class NavAdmin extends Component {    
    
    render() {
        return (  
         
                <React.Fragment>
                    <NavAdminMenu 
                        location={this.props.location} 
               
                    />
                   
                </React.Fragment>
    
        );
    }
}


export default withRouter(NavAdmin);
*/

import React from 'react';
 
import { NavLink } from 'react-router-dom';
 
const NavAdmin = () => {
    return (
       <div>
         <ul>
            <li>
          <NavLink to="/InsertUser">Insert User</NavLink>
          </li>
          <li>
          <NavLink to= "/UpdateUser">Update User</NavLink>
          </li>
          <li>
          <NavLink to= "/UserState">User State</NavLink>
          </li>
          </ul>
       </div>
    );
}
 
export default NavAdmin;
