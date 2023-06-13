const express = require('express')
const mysql = require('mysql')

module.exports = function getConnection() {
    return mysql.createConnection({
      host: 'localhost',
      user: '###',
      password: '###',
      database: '###',
      multipleStatements: true
    })
  }
