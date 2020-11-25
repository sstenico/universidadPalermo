const express = require("express");
const bodyParser = require('body-parser');
const app = express();
const mongoose = require('mongoose');
require ('dotenv/config');

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
/*

app.put('/paciente', function (req, res) {
 if(!req.body.nombre || !req.body.apellido) {
  respuesta = {
   error: true,
   codigo: 502,
   mensaje: 'El campo nombre y apellido son requeridos'
  };
 } else {
  if(paciente.nombre === '' || paciente.apellido === '') {
   respuesta = {
    error: true,
    codigo: 501,
    mensaje: 'El paciente no ha sido creado'
   };
  } else {
    paciente = {
    nombre: req.body.nombre,
    apellido: req.body.apellido,
    covid: req.body.covid,
    vacuna: req.body.vacuna
   };
   respuesta = {
    mensaje: 'Paciente actualizado',
    respuesta: paciente
   };
  }
 }
 
 res.send(respuesta);
});


*/

// conexion db
mongoose.connect(
  process.env.db_connection,
  {useNewUrlParser : true}, ()=>
  console.log('conectado db')
);

app.listen(3000, () => {
 console.log("El servidor está inicializado en el puerto 3000");
});