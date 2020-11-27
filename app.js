const express = require("express");
const bodyParser = require('body-parser');
const app = express();
const mongoose = require('mongoose');
require ('dotenv/config'); //para levantar el archivo .env

app.use(bodyParser.json());

const pacienteRoute = require('./routes/paciente');
app.use('/paciente',pacienteRoute);

let respuesta = {
 error: false,
 codigo: 200,
 mensaje: ''
};

app.get('/', function(req, res) {
 respuesta = {
  mensaje: 'Pagina Inicio - API COVID'
 };
 res.send(respuesta);
});

app.use(function(req, res, next) {
  respuesta = {
   error: true, 
   codigo: 404, 
   mensaje: 'URL no encontrada'
  };
  res.status(404).send(respuesta);
 });

// conexion db
mongoose.connect(
  process.env.db_connection,
  {useNewUrlParser : true}, ()=>
  console.log('conectado db')
);

app.listen(process.env.port_http, () => {
 console.log("El servidor está inicializado en el puerto 3000");
});