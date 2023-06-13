const express = require('express')
const router = express.Router()
const mysql = require('mysql')
const connect = require('../connect')
const jwt = require('jsonwebtoken')
const bodyParser = require('body-parser')
const bcrypt = require('bcryptjs')
router.use(bodyParser.urlencoded({ extended: false }))
router.use(bodyParser.json())


//---------------------Date-------------------------
router.get('/energy/api/ActualvsForecast/:areaname/:rescode/date/:date', verifyToken, (req, res) => {
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



            const querySt = `SELECT a.AreaName, ar.AreaTypeCodeText, m.MapCodeText, r.ResolutionCodeText, a.Year, a.Month, a.Day, a.DateTime, a.TotalLoadValue a_value, d.d_value 
        FROM ActualTotalLoad a INNER JOIN ResolutionCode r ON a.ResolutionCodeId=r.Id
        INNER JOIN AreaTypeCode ar ON a.AreaTypeCodeId=ar.Id
        INNER JOIN MapCode m ON a.MapCodeId=m.Id  
        INNER JOIN (SELECT da.DateTime, da.TotalLoadValue d_value
            FROM DayAheadTotalLoadForecast da INNER JOIN ResolutionCode r ON da.ResolutionCodeId=r.Id
            INNER JOIN AreaTypeCode ar ON da.AreaTypeCodeId=ar.Id
            INNER JOIN MapCode m ON da.MapCodeId=m.Id  
            WHERE da.AreaName = ? AND DATE(da.DateTime)= '${date}' AND r.ResolutionCodeText = ?
            ORDER BY da.DateTime ASC) d ON d.DateTime = a.DateTime 
        WHERE a.AreaName = ? AND DATE(a.DateTime)= DATE('${date}') AND r.ResolutionCodeText = ?
        ORDER BY a.DateTime ASC`;
            db.query(querySt, [area, resc, area, resc], (err, rows, fields) => {
                if (err) throw err
                const result = rows.map((row) => {
                    return {
                        Source: 'entso-e', Dataset: 'ActualvsForecastedTotalLoad', AreaName: row.AreaName,
                        AreaTypeCode: row.AreaTypeCodeText, MapCode: row.MapCodeText, ResolutionCode: row.ResolutionCodeText,
                        Year: row.Year, Month: row.Month, Day: row.Day, DateTimeUTC: row.DateTime, DayAheadTotalLoadForecastValue: row.d_value, ActualTotalLoadValue: row.a_value
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

//---------------------Month------------------------
router.get('/energy/api/ActualvsForecast/:areaname/:rescode/month/:date', verifyToken, (req, res) => {
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
            let apikey = req.query.apikey
            date += "-01"
            const querySt = `SELECT s2.Day, s1.AreaName, s1.AreaTypeCodeText, s1.MapCodeText, s1.ResolutionCodeText,s1.Year, s1.Month , s2.A_Sum , DayAhead.D_Sum  
      FROM (SELECT DISTINCT Day, AreaName,AreaTypeCodeText,MapCodeText,ResolutionCodeText, Year, Month 
             FROM ActualTotalLoad a INNER JOIN ResolutionCode r ON a.ResolutionCodeId=r.Id
                                    INNER JOIN AreaTypeCode ar ON a.AreaTypeCodeId=ar.Id
                                    INNER JOIN MapCode m ON a.MapCodeId=m.Id
             WHERE a.AreaName = ? AND a.Month= Month('${date}') AND r.ResolutionCodeText = ?
           ORDER BY Day ASC) s1 INNER JOIN (SELECT SUM(TotalLoadValue) AS A_Sum,Day
                                              FROM ActualTotalLoad
                            GROUP BY Day
                            ORDER BY Day ASC) s2 ON s1.Day=s2.Day
      INNER JOIN (SELECT s4.Day,s4.D_Sum  
            FROM (SELECT DISTINCT Day, AreaName,AreaTypeCodeText,MapCodeText,ResolutionCodeText,Year,Month 
                FROM DayAheadTotalLoadForecast a INNER JOIN ResolutionCode r ON a.ResolutionCodeId=r.Id
                                                         INNER JOIN AreaTypeCode ar ON a.AreaTypeCodeId=ar.Id
                                                         INNER JOIN MapCode m ON a.MapCodeId=m.Id
                  WHERE AreaName = ? AND Month= Month('${date}') AND ResolutionCodeText = ?
                        ORDER BY Day ASC) s3 INNER JOIN (SELECT SUM(TotalLoadValue) AS D_Sum,Day 
                                                         FROM DayAheadTotalLoadForecast
                                                         GROUP BY Day
                                                         ORDER BY Day ASC) s4 ON s3.Day=s4.Day) DayAhead ON DayAhead.Day = s1.Day;`;
            db.query(querySt, [area, resc, area, resc], (err, rows, fields) => {
                if (err) throw err
                const result = rows.map((row) => {
                    return {
                        Source: 'entso-e', Dataset: 'ActualvsForecastedTotalLoad', AreaName: row.AreaName,
                        AreaTypeCode: row.AreaTypeCodeText, MapCode: row.MapCodeText, ResolutionCode: row.ResolutionCodeText,
                        Year: row.Year, Month: row.Month, Day: row.Day, DateTimeUTC: row.DateTime, DayAheadTotalLoadForecastByDayValue: row.D_Sum, ActualTotalLoadByDayValue: row.A_Sum

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

//---------------------Year-------------------------
router.get('/energy/api/ActualvsForecast/:areaname/:rescode/year/:date', verifyToken, (req, res) => {
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
            let apikey = req.query.apikey
            const querySt = `SELECT s1.Month, s1.AreaName, s1.AreaTypeCodeText, s1.MapCodeText, s1.ResolutionCodeText, s1.Year, s2.A_Sum, DayAhead.D_Sum
      FROM (SELECT DISTINCT Month, AreaName,AreaTypeCodeText,MapCodeText,ResolutionCodeText,Year
            FROM ActualTotalLoad a INNER JOIN ResolutionCode r ON a.ResolutionCodeId=r.Id
                                   INNER JOIN AreaTypeCode ar ON a.AreaTypeCodeId=ar.Id
                       INNER JOIN MapCode m ON a.MapCodeId=m.Id
          WHERE AreaName = ? AND Year=? AND ResolutionCodeText = ?
          ORDER BY Month ASC) s1 INNER JOIN (SELECT SUM(TotalLoadValue) AS A_Sum,Month 
                                               FROM ActualTotalLoad
                                               GROUP BY Month
                           ORDER BY Month ASC) s2 ON s1.Month=s2.Month
                     INNER JOIN (SELECT s3.Month, s4.D_Sum
                           FROM (SELECT DISTINCT Month, AreaName,AreaTypeCodeText,MapCodeText,ResolutionCodeText,Year
                                                      FROM DayAheadTotalLoadForecast a INNER JOIN ResolutionCode r ON a.ResolutionCodeId=r.Id
                                                                 INNER JOIN AreaTypeCode ar ON a.AreaTypeCodeId=ar.Id
                                               INNER JOIN MapCode m ON a.MapCodeId=m.Id
                                                      WHERE AreaName = ? AND Year=? AND ResolutionCodeText = ?
                                                      ORDER BY Month ASC) s3 INNER JOIN (SELECT SUM(TotalLoadValue) AS D_Sum,Month 
                                                                                         FROM DayAheadTotalLoadForecast
                                                                                         GROUP BY Month
                                                                                         ORDER BY Month ASC) s4 ON s3.Month=s4.Month) DayAhead ON DayAhead.Month = s1.Month`;
            db.query(querySt, [area, date, resc, area, date, resc], (err, rows, fields) => {
                if (err) throw err
                const result = rows.map((row) => {
                    return {
                        Source: 'entso-e', Dataset: 'ActualvsForecastedTotalLoad', AreaName: row.AreaName,
                        AreaTypeCode: row.AreaTypeCodeText, MapCode: row.MapCodeText, ResolutionCode: row.ResolutionCodeText,
                        Year: row.Year, Month: row.Month, Day: row.Day, DateTimeUTC: row.DateTime, DayAheadTotalLoadForecastByMonthValue: row.D_Sum, ActualTotalLoadByMonthValue: row.A_Sum
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
