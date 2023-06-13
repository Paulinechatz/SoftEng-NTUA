const express = require('express')
const app = express()
const https = require('https')
const fs = require('fs')

const options = {
  key: fs.readFileSync('privatekey.pem'),
  cert: fs.readFileSync('certificate.pem')
};

//localhost:8765
https.createServer(options, app).listen(8765)

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
  res.header("Access-Control-Allow-Headers", "Origin, x-observatory-auth, Content-Type, Accept, Authorization");
  next();
});

//--------------ActualTotalLoad----------------
const actualTotalLoad = require('./routes/data/actualTotalLoad.js')
app.use(actualTotalLoad)


//-----------DayAheadTotalForecast-------------
const dayAhead = require('./routes/data/dayAhead.js')
app.use(dayAhead)


//-----------AllTypes(AggregatedGen)-----------
const allTypes = require('./routes/data/allTypes.js')
app.use(allTypes)


//---------AggregatedGenerationPerType---------
const aggregatedGen = require('./routes/data/aggregatedGen.js')
app.use(aggregatedGen)


//-------------ActualvsForecast-----------------
const actualVsForecast = require('./routes/data/actualVsForecast.js')
app.use(actualVsForecast)


//----------------HealthCheck-------------------
const health = require('./routes/healthCheck.js')
app.use(health)


//-------------------Reset----------------------
const reset = require('./routes/reset.js')
app.use(reset)


//----------------Login/Logout------------------
const log = require('./routes/log.js')
app.use(log)

//const noauth = require('./routes/no-auth.js')
//app.use(noauth)


//const noauth = require('./routes/no-auth.js')
//app.use(noauth)


//-------------------Admin----------------------
const admin = require('./routes/admin.js')
app.use(admin)


//Homepage
app.get("/energy/api", (req, res) => {
  res.send("Project Base-URL")
})


