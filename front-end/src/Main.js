import React, { Component } from 'react';
//import { HashRouter as Router, Route, Redirect } from "react-router-dom";
//import {Actual} from './Actual';

import { BrowserRouter, Route, Switch } from 'react-router-dom';
 

//import About from './About';
//import Contact from './Contact';
import {Actual} from './Actual';
import {Aggregate} from './Aggregate';
import {DayAhead} from './DayAhead';
import {Vs} from './Vs';

import Navigation from './Navigation';

//route link from navigation connects to {page}
class Main extends Component {
  render() {
    return (      
       <BrowserRouter>
        <div>
          <Navigation />
            <Switch>
             <Route path="/ActualTotalLoad" component={Actual}/>
             <Route path="/AggregatedGenerationPerType" component={Aggregate}/>
             <Route path="/DayAheadTotalLoadForecast" component={DayAhead}/>
             <Route path="/ActualvsForecast" component={Vs}/>
           </Switch>
        </div> 
      </BrowserRouter>
    );
  }
}
  export default Main;







  
/*
    render() {
        return (      
           <BrowserRouter>
            <div>
              <Navigation />
                <Switch>
                 <Route path="/about" component={About}/>
                 <Route path="/contact" component={Contact}/>
               </Switch>
            </div> 
          </BrowserRouter>
        );
      }
*/



/*
    redirectToTarget1 = () => {
        this.props.history.push(`/ActualTotalLoad`)
        
      }

    redirectToTarget2 = () => {
        this.props.history.push(`/AggregatedGenerationPerType`)
      }

    redirectToTarget3 = () => {
        this.props.history.push(`/DayAheadTotalLoadForecast`)
      }

    redirectToTarget4 = () => {
        this.props.history.push(`/ActualvsForecast`)
      }*/
      /*
    render() {
        return (
        <div>
            <div>

                <div className='row'>
                    <h1>
                        Main page
                    </h1>                
                </div>

                
                    <div className="column">
                    <button className="btn btn-primary" onClick={this.redirectToTarget1} >
                        Actual
                    </button>
                    </div>

                    <div className="column">
                    <button className="btn btn-secondary" onClick={this.redirectToTarget2}>
                        Aggregate
                    </button>
                    </div>

                    <div className="column">
                    <button className="btn btn-primary" onClick={this.redirectToTarget3}>
                        DayAhead
                    </button>
                    </div>

                    <div className="column">
                    <button className="btn btn-secondary" onClick={this.redirectToTarget4}>
                        Vs
                    </button>
                    </div>



            </div>
            

           <form action="http://google.com">
                <input type="submit" value="Go to Google" />
          </form>

          </div> 


        );
    }
    */
/*
                    <Route path="/ActualTotalLoad" component={Actual} />
*/

/*

                    <div className="content">
                        <Route path="/ActualTotalLoad" component={Actual}/>
                        
                    </div>
                    */

                    /*
                    Do wrapping in the reverse way and you get the original button with the Link attached. No CSS changes required.

 <Link to="/dashboard">
     <button type="button">
          Click Me!
     </button>
 </Link>
Here button is HTML button. It is also applicable to the components imported from third party libraries like Semantic-UI-React.

 import { Button } from 'semantic-ui-react'
 ... 
 <Link to="/dashboard">
     <Button style={myStyle}>
        <p>Click Me!</p>
     </Button>
 </Link>
                    */
                   /*<div>
    <h1>
    Hi
</h1>
          <Router>
              <div className='container'>
              <Route path="/Paris" component={Paris} />

              </div>
              </Router>
              </div>*/