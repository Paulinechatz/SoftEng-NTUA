const express = require('express')
const router = express.Router()
const mysql = require('mysql')
const connect = require('../connect')
const jwt = require('jsonwebtoken')
const bodyParser = require('body-parser')
const bcrypt = require('bcryptjs')
router.use(bodyParser.urlencoded({ extended: false }))
router.use(bodyParser.json())

//-----------------------Date-------------------------
router.get('/energy/api/AggregatedGenerationPerType/:areaname/AllTypes/:rescode/date/:date', verifyToken, (req, res) => {
    jwt.verify(req.token, 'test', async (err, authData) => {
        if (err) res.status(401).send('Not authorized')
        else {
            const uname = authData.username
            const db = connect()
            const query1 = `SELECT curr_quota FROM Users WHERE username = '${uname}'`;
            db.query(query1, (err, rows) => {
                if (err) throw err
                if (rows[0].curr_quota == 0) res.status(402).send("Out of quota")
                else {
                    const query2 = `UPDATE Users SET curr_quota = curr_quota-1 WHERE username = '${uname}'`;
                    db.query(query2, (err) => {
                        if (err) throw err
                    })
                }

            })
            const area = req.params.areaname
            const resc = req.params.rescode
            var date = req.params.date
            let format = req.query.format
            const querySt = `SELECT AreaName, AreaTypeCodeText,MapCodeText,ResolutionCodeText,Year,Month,Day,DateTime,
            ProductionTypeText,ActualGenerationOutput,UpdateTime
            FROM AggregatedGenerationPerType a 
      INNER JOIN ResolutionCode r ON a.ResolutionCodeId=r.Id 
      INNER JOIN AreaTypeCode ar ON a.AreaTypeCodeId=ar.Id 
      INNER JOIN MapCode m ON a.MapCodeId=m.Id 
      INNER JOIN ProductionType p ON a.ProductionTypeId=p.Id
      WHERE
          AreaName = ? AND DATE(DateTime)= '${date}' AND ResolutionCodeText =?
          ORDER BY DateTime ASC , ProductionTypeText ASC`;
            db.query(querySt, [area, resc], (err, rows, fields) => {
                if (err) throw err
                const result = rows.map((row) => {
                    return {
                        Source: 'entso-e', Dataset: 'AggregatedGenerationPerType', AreaName: row.AreaName,
                        AreaTypeCode: row.AreaTypeCodeText, MapCode: row.MapCodeText, ResolutionCode: row.ResolutionCodeText,
                        Year: row.Year, Month: row.Month, Day: row.Day, DateTimeUTC: row.DateTime, ProductionType: row.ProductionTypeText,
                        ActualGenerationOutputValue: row.ActualGenerationOutput, UpdateTimeUTC: row.UpdateTime
                    }
                })
                if (format && format === 'csv') {
                    const csvData = objectToCSV(result)
                    res.set('Content-Type', 'text/csv')
                    res.send(csvData)

                }
                else res.json(result)
            })
        }
    })
})

//-----------------------Month-------------------------
router.get('/energy/api/AggregatedGenerationPerType/:areaname/AllTypes/:rescode/month/:date', verifyToken, (req, res) => {
    jwt.verify(req.token, 'test', async (err, authData) => {
        if (err) res.status(401).send('Not authorized')
        else {
            const uname = authData.username
            const db = connect()
            const query1 = `SELECT curr_quota FROM Users WHERE username = '${uname}'`;
            db.query(query1, (err, rows) => {
                if (err) throw err
                if (rows[0].curr_quota == 0) res.status(402).send("Out of quota")
                else {
                    const query2 = `UPDATE Users SET curr_quota = curr_quota-1 WHERE username = '${uname}'`;
                    db.query(query2, (err) => {
                        if (err) throw err
                    })
                }

            })
            const area = req.params.areaname
            const resc = req.params.rescode
            var date = req.params.date
            let format = req.query.format
            date += "-01"
            const querySt = `SELECT * FROM
        (SELECT DISTINCT Day,Month, AreaName,AreaTypeCodeText,MapCodeText,ResolutionCodeText,Year,ProductionTypeText
          FROM AggregatedGenerationPerType a
          INNER JOIN ResolutionCode r ON a.ResolutionCodeId=r.Id
          INNER JOIN AreaTypeCode ar ON a.AreaTypeCodeId=ar.Id
          INNER JOIN MapCode m ON a.MapCodeId=m.Id
          INNER JOIN ProductionType p ON a.ProductionTypeId=p.Id
        WHERE
            AreaName = ? AND Month=MONTH('${date}') AND ResolutionCodeText = ?
            ORDER BY Day ASC) s1 INNER JOIN
        (SELECT SUM(ActualGenerationOutput) AS Sum, Day,ProductionTypeText FROM AggregatedGenerationPerType a INNER JOIN ProductionType p ON a.ProductionTypeId=p.Id
        GROUP BY ProductionTypeText,Day
        ORDER BY Day ASC) s2 ON s1.Day=s2.Day AND s1.ProductionTypeText=s2.ProductionTypeText`;
            db.query(querySt, [area, resc], (err, rows, fields) => {
                if (err) throw err
                const result = rows.map((row) => {
                    return {
                        Source: 'entso-e', Dataset: 'AggregatedGenerationPerType', AreaName: row.AreaName,
                        AreaTypeCode: row.AreaTypeCodeText, MapCode: row.MapCodeText, ResolutionCode: row.ResolutionCodeText,
                        Year: row.Year, Month: row.Month, Day: row.Day, ProductionType: row.ProductionTypeText, ActualGenerationOutputByDayValue: row.Sum
                    }
                })
                if (format && format === 'csv') {
                    const csvData = objectToCSV(result)
                    res.set('Content-Type', 'text/csv')
                    res.send(csvData)

                }
                else res.json(result)
            })
        }
    })
})

//-----------------------Year--------------------------
router.get('/energy/api/AggregatedGenerationPerType/:areaname/AllTypes/:rescode/year/:date', verifyToken, (req, res) => {
    jwt.verify(req.token, 'test', async (err, authData) => {
        if (err) res.status(401).send('Not authorized')
        else {
            const uname = authData.username
            const db = connect()
            const query1 = `SELECT curr_quota FROM Users WHERE username = '${uname}'`;
            db.query(query1, (err, rows) => {
                if (err) throw err
                if (rows[0].curr_quota == 0) res.status(402).send("Out of quota")
                else {
                    const query2 = `UPDATE Users SET curr_quota = curr_quota-1 WHERE username = '${uname}'`;
                    db.query(query2, (err) => {
                        if (err) throw err
                    })
                }

            })
            const area = req.params.areaname
            const resc = req.params.rescode
            var date = req.params.date
            let format = req.query.format
            const querySt = `SELECT * FROM
        (SELECT DISTINCT Month, AreaName,AreaTypeCodeText,MapCodeText,ResolutionCodeText,Year,ProductionTypeText
          FROM AggregatedGenerationPerType a
          INNER JOIN ResolutionCode r ON a.ResolutionCodeId=r.Id
          INNER JOIN AreaTypeCode ar ON a.AreaTypeCodeId=ar.Id
          INNER JOIN MapCode m ON a.MapCodeId=m.Id
          INNER JOIN ProductionType p ON a.ProductionTypeId=p.Id
        WHERE
            AreaName = ? AND Year=? AND ResolutionCodeText = ?
            ORDER BY Month ASC) s1 INNER JOIN
        (SELECT SUM(ActualGenerationOutput) AS Sum, Month,ProductionTypeText FROM AggregatedGenerationPerType a INNER JOIN ProductionType p ON a.ProductionTypeId=p.Id
        GROUP BY ProductionTypeText,Month
        ORDER BY Month ASC) s2 ON s1.Month=s2.Month AND s1.ProductionTypeText=s2.ProductionTypeText`;
            db.query(querySt, [area, date, resc], (err, rows, fields) => {
                if (err) throw err
                const result = rows.map((row) => {
                    return {
                        Source: 'entso-e', Dataset: 'AggregatedGenerationPerType', AreaName: row.AreaName,
                        AreaTypeCode: row.AreaTypeCodeText, MapCode: row.MapCodeText, ResolutionCode: row.ResolutionCodeText,
                        Year: row.Year, Month: row.Month, ProductionType: row.ProductionTypeText, ActualGenerationOutputByMonthValue: row.Sum
                    }
                })
                if (format && format === 'csv') {
                    const csvData = objectToCSV(result)
                    res.set('Content-Type', 'text/csv')
                    res.send(csvData)

                }
                else res.json(result)
            })
        }
    })
})

const objectToCSV = function (data) {
    // TODO: move to a lib.  global plugin or mixin.
    const csvRows = []
    // get headers
    const headers = Object.keys(data[0])
    csvRows.push(headers.join(','))
    // loop over values
    for (const row of data) {
        const values = headers.map(header => {
            const escaped = ('' + row[header]).replace(/"/g, '\\"')
            return `"${escaped}"`
        })
        csvRows.push(values.join(','))
    }
    // join and return all lines
    return csvRows.join('\n')
}

function verifyToken(req, res, next) {
    const authToken = req.headers['x-observatory-auth']
    if (typeof authToken !== 'undefined') {
        req.token = authToken
        next()
    }
    else res.status(401).send('Not authorized')
}

module.exports = router