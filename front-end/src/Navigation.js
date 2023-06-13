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
    class NavigationMenu extends Component {
        render(){
    return (
       <div>
         <ul>
          <li>
          <NavLink label="Actual Total Load" to="/ActualTotalLoad" location={this.props.location.pathname} />
          </li>
          <li>
          <NavLink label="Aggregated Generation Per Type" to= "/AggregatedGenerationPerType" location={this.props.location.pathname}/>
          </li>
          <li>
          <NavLink label="Day Ahead Total Load Forecast" to="/DayAheadTotalLoadForecast" location={this.props.location.pathname} />           
          </li>
          <li>
          <NavLink label="Actual vs Forecast" to= "/ActualvsForecast" location={this.props.location.pathname}/> 
          </li>
         </ul>
       </div>
    );
}
    }

    class Navigation extends Component {    
    
    render() {
        return (  
         
                <React.Fragment>
                    <NavigationMenu 
                        location={this.props.location} 
               
                    />
                   
                </React.Fragment>
    
        );
    }
}


export default withRouter(Navigation);
*/
import React from 'react';
 
import { NavLink } from 'react-router-dom';
 
const Navigation = () => {
    return (
       //do link /... >title
       <div>
         <ul>
         <li>
          <NavLink to="/ActualTotalLoad"> Actual Total Load</NavLink>
          </li>
          <li>
          <NavLink to= "/AggregatedGenerationPerType"> Aggregated Generation Per Type</NavLink>
          </li>
          <li>
          <NavLink to="/DayAheadTotalLoadForecast"> Day Ahead Total Load Forecast </NavLink>
          </li>
          <li>
          <NavLink to= "/ActualvsForecast"> Actual vs Forecast</NavLink>
          </li>    
          </ul>
       </div>
    );
}
 
export default Navigation;