import React, { Component } from 'react';
import { UserContext } from './UserContext';

export class Actual extends Component {     
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

    //buttons//////////////////////////////////////////////////////
    handleSubmit(event){
        const area=this.state.area;
        const resolution=this.state.resolution;
        const year=this.state.year;
        const month=this.state.month;
        const day=this.state.day;

        //date//////////////////////////////////////////////////////
        if(day!==null && month!==null && year!==null){
        fetch(`https://localhost:8765/energy/api/ActualTotalLoad/${area}/${resolution}/date/${year}-${month}-${day}`, {
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
                //this.props.history.push('/ActualTotalLoad '+ area + resolution +'/date'+year+'-'+month+'-'+day);
            })
            .catch(error => {
                console.log("error", error);
            });
   
        }
    //month//////////////////////////////////////////////////////
    else if (month!==null && year!==null && day===null){
        fetch(`https://localhost:8765/energy/api/ActualTotalLoad/${area}/${resolution}/month/${year}-${month}`, {
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
               // this.props.history.push('/ActualTotalLoad');
            })
        }

    //year//////////////////////////////////////////////////////
    else if (month===null && year!==null && day===null){
        fetch(`https://localhost:8765/energy/api/ActualTotalLoad/${area}/${resolution}/year/${year}`, {
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
                 <div className= "Actual">
                                 <ul>
                                     {this.state.items.map(item =>(

                                     <li>
                                       Dataset: {item.Dataset} | AreaName: {item.AreaName}| Resolution: {item.ResolutionCode} | Year: {item.Year} | Month: {item.Month} | Day: {item.Day} | Time: {item.UpdateTimeUTC} | Actual Load: {item.ActualTotalLoadValue}
                                     </li>
                                     ))};
                                 </ul>
                             </div>
                     );
     
         }
        
         else if(!this.state.d && this.state.m && this.state.y){
            return(
        <div className= "Actual">
                        <ul>
                            {this.state.items.map(item =>(
                            <li>
                              Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.ResolutionCode} | Year: {item.Year} | Month: {item.Month} | Actual Load: {item.ActualTotalLoadByDayValue}
                            </li>
                            ))};
                        </ul>
                    </div>
            );

}
    

 
    else if (!this.state.d && !this.state.m && this.state.y){
    return(
<div className= "Actual">
                <ul>
                    {this.state.items.map(item =>(
                    <li>
                      Dataset: {item.Dataset} | AreaName: {item.AreaName} | Resolution: {item.ResolutionCode} | Year: {item.Year} | Actual Load: {item.ActualTotalLoadByMonthValue}
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
        <h1>Actual Total Load</h1> 
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
        <input type="int" name="day" placeholder="Day" value={this.state.day} onChange={this.handleChange} />
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
export default Actual ; 

    /*
     <li>        
                <select  type="text" name= "resolution" placeholder="Resolution" value={this.state.resolution} onChange={this.handleChange}>
                    <option value="PT60M">PT60M</option>
                    <option value="PT30M">PT30M</option>
                    <option value="PT15M">PT15M</option>
                </select>
    
                </li>
    <li>        
                <select type="int" name= "month" placeholder="Month" value={this.state.month} onChange={this.handleChange}>
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    </select>
    
    </li>


        <li>        
                <select type="int" name= "day" placeholder="Day" value={this.state.day} onChange={this.handleChange}>
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="24">24</option>
                    <option value="25">25</option>
                    <option value="26">26</option>
                    <option value="27">27</option>
                    <option value="28">28</option>
                    <option value="29">29</option>
                    <option value="30">30</option>
                    <option value="31">31</option>
                </select>
    
                </li>
                */