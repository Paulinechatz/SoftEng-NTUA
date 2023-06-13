const express = require('express')
const router = express.Router()
const mysql = require('mysql')
const connect = require('./connect')

router.post('/energy/api/Reset' , (req,res) =>{
    const db= connect()
    query1 = `DELETE FROM Users WHERE username != 'admin';DELETE FROM MapCode; DELETE FROM AreaTypeCode; 
    DELETE FROM ProductionType; DELETE FROM ResolutionCode; 
    DELETE FROM AllocatedEICDetail; DELETE FROM DayAheadTotalLoadForecast; 
    DELETE FROM DayAheadTotalLoadForecast; DELETE FROM AggregatedGenerationPerType; DELETE FROM ActualTotalLoad;`
  db.query(query1, (err, rows, fields) => {
    if (err) throw err 
    var result = {status : "OK"}
        res.json(result)

  })
})
  
  module.exports = router
