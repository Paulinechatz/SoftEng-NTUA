import React, {Component} from 'react';
import {Bar, Line, Pie} from 'react-chartjs-2';

class Chart extends Component{
    constructor(props){
        super(props);
        this.state={
            chartData: props.chartData
        }
    }
    
    render(){
        return(
            <div className="chart">
                <Bar 
                data={this.state.chartData}
                option={{}}  
                />

            </div>
        )
    }

}

export default Chart;