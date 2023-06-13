import React, { Component } from 'react';
//import Select from 'react';
import { UserContext } from './UserContext';

/*
const Types= [
    {label: "Fossil Gas" , value: "Fossil Gas"},
    {label: "Hydro Run-of-river and poundage", value: "Hydro Run-of-river and poundage"},
    {label: "Hydro Pumped Storage", value:"Hydro Pumped Storage" },
    {label:"Hydro Water Reservoir", value: "Hydro Water Reservoir"},
    {label:"Fossil Hard coal", value: "Fossil Hard coal"},
    {label:"Nuclear" , value: "Nuclear"},
    {label:"Fossil Brown coal/Lignite" , value:"Fossil Brown coal/Lignite" },
    {label: "Fossil Oil", value:"Fossil Oil" },
    {label: "Fossil Oil shale", value: "Fossil Oil shale"},
    {label: "Biomass", value:"Biomass" },
    {label: "Fossil Peat", value: "Fossil Peat"},
    {label: "Wind Onshore", value: "Wind Onshore"},
    {label: "Other", value:"Other" },
    {label: "Wind Offshore", value: "Wind Offshore"},
    {label: "Fossil Coal-derived gas", value: "Fossil Coal-derived gas"},
    {label: "Waste", value:"Waste" },
    {label: "Solar", value:"Solar" },
    {label: "Geothermal", value:"Geothermal" },
    {label: "Other renewable", value:"Other renewable" },
    {label: "Marine", value:"Marine" },
    {label: "AC Link", value:"AC Link" },
    {label: "Transformer", value:"Transformer" },
    {label: "DC Link", value:"DC Link" },
    {label: "Substation", value:"Substation" },

];

*/

export class Aggregate extends Component {        
    
    static contextType = UserContext;
    
    constructor(props) {
        super(props);
        this.state = {
            area: "",
            ttype: "",
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
    
    handleSubmit(event) {
        //console.log('ref to username: ', this.username.current);
        const area=this.state.area;
        const ttype=this.state.ttype;
        const resolution=this.state.resolution;
        const year=this.state.year;
        const month=this.state.month;
        const day=this.state.day;
        

        //date//////////////////////////////////////////////////////
        if(day!==null && month!==null && year!==null){
            fetch(`https://localhost:8765/energy/api/AggregatedGenerationPerType/${area}/${ttype}/${resolution}/date/${year}-${month}-${day}`,{
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
        
            fetch(`https://localhost:8765/energy/api/AggregatedGenerationPerType/${area}/${ttype}/${resolution}/month/${year}-${month}`,{
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
            fetch(`https://localhost:8765/energy/api/AggregatedGenerationPerType/${area}/${ttype}/${resolution}/year/${year}`,{
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
      
        event.preventDefault();
    }
    
    renderIf(){
      
        if(!this.state.isloaded ){
            return <div> Is loading....</div>
        }
        else if(this.state.d && this.state.m && this.state.y){
            return(
                <div className= "Aggregate">
                    <ul>
                        {this.state.items.map(item =>(
                            <li>
                                Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.ResolutionCode} | Year: {item.Year} | Month: {item.Month} | Day: {item.Day} | Time: {item.UpdateTimeUTC} | Production Type: {item.ProductionType} | Load: {item.ActualGenerationOutputValue}
                            </li>
                        ))};
                    </ul>
                </div>
            );
        }
    
        else if(!this.state.d && this.state.m && this.state.y){
            return(
                <div className= "Aggregate">
                    <ul>
                        {this.state.items.map(item =>(
                            <li>
                                Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.ResolutionCode} | Year: {item.Year} | Month: {item.Month} | Production Type: {item.ProductionType} | Load: {item.ActualGenerationOutputByDayValue}
                            </li>
                        ))};
                    </ul>
                </div>
            );
        }

        else if (!this.state.d && !this.state.m && this.state.y){
            return(
                <div className= "Aggregate">
                    <ul>
                        {this.state.items.map(item =>(
                            <li>
                                Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.ResolutionCode} | Year: {item.Year} | Production Type: {item.ProductionType} | Load: {item.ActualGenerationOutputByMonthValue}
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
                <h1>Aggregated Generation Per Type</h1> 
                <form onSubmit={this.handleSubmit}>
                    <li>
                        <input type="text" name="area" placeholder="AreaName" value={this.state.area} onChange={this.handleChange} required />
                    </li>
                    <li>
                       <input type="text" name="ttype"  placeholder="ProductionType" value={this.state.ttype} onChange={this.handleChange} required />
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

export default Aggregate;

//<Select options={Types} />