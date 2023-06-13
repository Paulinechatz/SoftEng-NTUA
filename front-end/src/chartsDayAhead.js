import React, { Component } from 'react';
import { UserContext } from './UserContext';
/*import CanvasJSReact from './canvasjs.react';
//var CanvasJSReact = require('./canvasjs.react');
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;
*/

import Chart from './Charts';
//import split from './math.js';

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
            d: false, 
            //chartData: [],
            //datasets: [],
            label:'',
            data: [],
            labels:[],
            backgroundColor:'rgba(255, 99, 132, 0.6)'
        }    
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        //this.getChartData= this.getChartData.bind(this);
    }

    //change the parameter////////////////////////////////////////////
    handleChange(event){

        this.setState({
            [event.target.name]: event.target.value
    
        })
    }
    /*getChartData(){
        this.setState({
            chartData:{
                labels:[],
                datasets:[
                    {
                        label:'Actual Total Load',
                        data:[],
                        backgroundColor:[
                            'rgba(255, 99, 132, 0.6)'
                        ]
    
                    }
                ]
            }
    
        })
    }*/
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
            //const lb = json.UpdateTimeUTC;
            //const da = json.DayAheadTotalLoadForecastValue;
            this.setState({
                items: json,
                isloaded: true,
                y: true,
                m: true, 
                d: true,
                //labels: [json.UpdateTimeUTC],
                //data: [json.DayAheadTotalLoadForecastValue],
                label: 'Actual Total Load',

                //datasets:[this.state.label, this.state.data],
                //chartData: [ this.state.labels, this.state.datasets]
                    

                /*chartData:{
                    labels:lb,
                    datasets:[
                        {
                            label:'Actual Total Load',
                            data: da,
                            backgroundColor:[
                                'rgba(255, 99, 132, 0.6)'
                            ]
        
                        }
                    ]
                }*/
            });
           // localStorage.setItem(labels, json.UpdateTimeUTC);
           // localStorage.setItem(data, json.DayAheadTotalLoadForecastValue);
           this.state.data.push(json.day);
        })
        //.then(()=>this.getChartData())
    }
             /*   this.setState({
                chartData:{
                    labels:['o', 'p'],
                    datasets:[
                        {
                            label:'Actual Total Load',
                            data: this.state.items.DayAheadTotalLoadForecastValue,
                            backgroundColor:[
                                'rgba(255, 99, 132, 0.6)'
                            ]
        
                        }
                    ]
                }
        
            });
            //this.props.history.push('/ActualTotalLoad');
      
    }*/

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
/*
componentWiilMount(){
    this.getChartData();
}

getChartData(){
    this.setState({
        chartData:{
            labels:[this.state.items.UpdateTimeUTC],
            datasets:[
                {
                    label:'Actual Total Load',
                    data:[this.state.items.DayAheadTotalLoadForecastValue],
                    backgroundColor:[
                        'rgba(255, 99, 132, 0.6)'
                    ]

                }
            ]
        }

    })
}*/

/*
renderIf(){
      
    if(!this.state.isloaded ){
        return <div> Is loading....</div>
    }
    else if(this.state.d && this.state.m && this.state.y){
        return(
            <div className= "DayAhead">
                
                {this.getChartData()}
                <Chart chartData={this.state.chartData}  legendPosition="bottom"/>


            </div>
        );
}
    
    else if(!this.state.d && this.state.m && this.state.y){
        return(
            <div className= "DayAhead">
                <ul>
                    {this.state.items.map(item =>(
                        <li>
                            Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.Resolution} | Year: {item.Year} | Month: {item.Month} | Load: {item.DayAheadTotalLoadForecastByDayValue}
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
                            Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.Resolution} | Year: {item.Year} | Load: {item.DayAheadTotalLoadForecastByMonthValue}
                        </li>
                    ))};
                </ul>
            </div>
        );
    }
}*/
/*
check(){
    if( (this.state.month === 01 || this.state.month === 03 ||this.state.month === 05 || this.state.month === 07 || this.state.month === 08 || this.state.month === 10 || this.state.month === 12 ) && (this.state.day < 01 || this.state.day > 31) ){
        return (<h1>Date Error</h1>)
    }
    else if( (this.state.month === 04 || this.state.month === 06 ||this.state.month === 09 || this.state.month === 11 ) && (this.state.day < 01 || this.state.day > 31) ){
        return (<h1>Date Error</h1>)
    }
    else if ( this.state.month === 02 && (this.state.day < 01 || this.state.day > 28)){
        return( <h1>Date Error</h1> )
    }
}
*/
/*
renderIf(){
      
    if(!this.state.isloaded ){
        return <div> Is loading....</div>
    }
    else if(this.state.d && this.state.m && this.state.y){
        return(
            
            <div className= "DayAhead">
                
                <Chart chartData={this.state.chartData}  legendPosition="bottom"/>


            </div>
        );
}
}*/


/// Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.Resolution} | Year: {item.Year} | Month: {item.Month} | Day: {item.Day}| Load: {item.DayAheadTotalLoadForecastValue}
      

renderIf(){
    //const Load=this.state.DayAheadTotalLoadForecastValue ;
    //const options={};
    const e=this.state.data;
    return(
   /*     <div>
    {this.state.items.map(item =>(
        options = {
      
            labels:item.UpdateTimeUTC,
            datasets:[
                {
                    label:'Actual Total Load',
                    data: item.DayAheadTotalLoadForecastValue,
                    backgroundColor:[
                        'rgba(255, 99, 132, 0.6)'
                    ]

                }
            ]
        }
        
    ))}
    <Chart chartData={options}  legendPosition="bottom" />

    </div>*/
    <li>{e} </li>
    );
}
 
   /*        
    const options = {
      
            labels:['j','h','y'],
            datasets:[
                {
                    label:'Actual Total Load',
                    data: Load,
                    backgroundColor:[
                        'rgba(255, 99, 132, 0.6)'
                    ]

                }
            ]
        }*/
      /*  return(
        <div>
            <Chart chartData={options}  legendPosition="bottom" />
             </div> 
        )

    
}*/




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



/*<form onClick={this.componentDidMount}>
                <button className="btn btn-primary" type="click">
                    Logout
                </button>*/

                ///<Chart chartData={this.state.chartData}  legendPosition="bottom"/>