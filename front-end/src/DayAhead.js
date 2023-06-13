import React, { Component } from 'react';
import { UserContext } from './UserContext';

export class DayAhead extends Component {        
    
    static contextType = UserContext;
    
    constructor(props) {
        super(props);
        this.state = {
            area: "",
            resolution: "",
            year: null,
            month: null,
            day: null, 
            items:[],
            isloaded: false, 
            y: false,
            m: false, 
            d: false
        }    
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    //change the parameter////////////////////////////////////////////
    handleChange(event){

        this.setState({
            [event.target.name]: event.target.value
    
        })
    }
    
   //handle the button//////////////////////////////////////////////////////
   handleSubmit(event){
    const area=this.state.area;
    const resolution=this.state.resolution;
    const year=this.state.year;
    const month=this.state.month;
    const day=this.state.day;



    //date//////////////////////////////////////////////////////
    if(day!==null && month!==null && year!==null){
        fetch(`https://localhost:8765/energy/api/DayAheadTotalLoadForecast/${area}/${resolution}/date/${year}-${month}-${day}`, {
            method: 'GET',
            headers:{
                'X-OBSERVATORY-AUTH': this.context.token,
                'Content-Type':'application/x-www-form-urlencoded',
              
            }
        })
        .then((response) => response.json())
        .then(json => { 
            this.setState({
                items: json,
                isloaded: true,
                y: true,
                m: true, 
                d: true,
            });
            //this.props.history.push('/ActualTotalLoad');
        })
    }

    //month//////////////////////////////////////////////////////
    else if (month!==null && year!==null && day===null){
        fetch(`https://localhost:8765/energy/api/DayAheadTotalLoadForecast/${area}/${resolution}/month/${year}-${month}`, {
            method: 'GET',
            headers:{
                'X-OBSERVATORY-AUTH': this.context.token,
                'Content-Type':'application/x-www-form-urlencoded',
              
            }
        })
        .then((response) => response.json())
        .then(json => { 
            this.setState({
                items: json,
                isloaded: true,
                y: true,
                m: true, 
                d: false,
            });
            //this.props.history.push('/ActualTotalLoad');
        })
    }

    //year//////////////////////////////////////////////////////
    else if (month===null && year!==null && day===null){
        fetch(`https://localhost:8765/energy/api/DayAheadTotalLoadForecast/${area}/${resolution}/year/${year}`, {
            method: 'GET',
            headers:{
                'X-OBSERVATORY-AUTH': this.context.token,
                'Content-Type':'application/x-www-form-urlencoded',   
            }
        })
        .then((response) => response.json())
        .then(json => { 
            this.setState({
                items: json,
                isloaded: true,
                y: true,
                m: false, 
                d: false,
            });
            //this.props.history.push('/ActualTotalLoad');
        })
    }
}
    

renderIf(){
      
    if(!this.state.isloaded ){
        return <div> Is loading....</div>
    }
    else if(this.state.d && this.state.m && this.state.y){
        return(
            <div className= "DayAhead">
                <ul>
                    {this.state.items.map(item =>(
                        <li>
                            Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.ResolutionCode} | Year: {item.Year} | Month: {item.Month} | Day: {item.Day}| Time: {item.UpdateTimeUTC} | Forecast Load: {item.DayAheadTotalLoadForecastValue}
                        </li>
                    ))};
                </ul>
            </div>
        );
}
    
    else if(!this.state.d && this.state.m && this.state.y){
        return(
            <div className= "DayAhead">
                <ul>
                    {this.state.items.map(item =>(
                        <li>
                            Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.ResolutionCode} | Year: {item.Year} | Month: {item.Month} |Forecast Load: {item.DayAheadTotalLoadForecastByDayValue}
                        </li>
                    ))};
                </ul>
            </div>
        );
    }

    else if (!this.state.d && !this.state.m && this.state.y){
        return(
            <div className= "DayAhead">
                <ul>
                    {this.state.items.map(item =>(
                        <li>
                            Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.ResolutionCode} | Year: {item.Year} | Forecast Load: {item.DayAheadTotalLoadForecastByMonthValue}
                        </li>
                    ))};
                </ul>
            </div>
        );
    }
}


render(){
    
    return(
        <div>
            <h1>Day Ahead Total Load Forecast</h1> 
            <form onSubmit={this.handleSubmit} >
    
                <li>
                    <input type="text" name="area" placeholder="AreaName" value={this.state.area} onChange={this.handleChange} required />
                </li>


                <li>
                    <input type="text" name="resolution" placeholder="Resolution" value={this.state.resolution} onChange={this.handleChange}  required />
                </li>

                <li>
                    <input type="int" name="year" placeholder="Year" value={this.state.year} onChange={this.handleChange}  required />
                </li>

                <li>
                    <input type="int" name="month" placeholder="Month" value={this.state.month} onChange={this.handleChange} />
                </li>

                <li>
                    <input type="int" name="day" placeholder="Day" value={this.state.day} onChange={this.handleChange}   />
                </li>

                <button className="btn btn-primary" type="submit">
                    Send
                </button>
            </form>

            {this.renderIf()}
        </div>
    );
}
}
export default DayAhead;