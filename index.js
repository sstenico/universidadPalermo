const express = require("express");
const bodyParser = require('body-parser');
const app = express();

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

let paciente = {
 nombre:'',
 apellido: '',
 covid :'',
 vacuna:''
};

let respuesta = {
 error: false,
 codigo: 200,
 mensaje: ''
};

app.get('/', function(req, res) {
 respuesta = {
  mensaje: 'Pagina Inicio'
 };
 res.send(respuesta);
});

app.get('/paciente', function (req, res) {
 respuesta = {
  error: false,
  codigo: 200,
  mensaje: ''
 };
 if(paciente.nombre === '' || paciente.apellido === '') {
  respuesta = {
   error: true,
   codigo: 501,
   mensaje: 'El paciente no ha sido creado'
  };
 } else {
  respuesta = {
   mensaje: 'respuesta del paciente',
   respuesta: paciente
  };
 }
 res.send(respuesta);
});


app.post('/paciente', function (req, res) {
 if(!req.body.nombre || !req.body.apellido) {
  respuesta = {
   error: true,
   codigo: 502,
   mensaje: 'El campo nombre y apellido son requeridos!!'
  };
 } else {
    paciente = {
    nombre: req.body.nombre,
    apellido: req.body.apellido,
    covid: req.body.covid,
    vacuna: req.body.vacuna
   };
    respuesta = {
    error: false,
    codigo: 200,
    mensaje: 'Paciente creado',
    respuesta: paciente
   };
 }
 res.send(respuesta);
});

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
app.delete('/paciente', function (req, res) {
 if(paciente.nombre === '' || paciente.apellido === '') {
  respuesta = {
   error: true,
   codigo: 501,
   mensaje: 'El paciente no ha sido creado'
  };
 } else {
  respuesta = {
   error: false,
   codigo: 200,
   mensaje: 'Paciente eliminado'
  };
  usuario = { 
   nombre: '', 
   apellido: '' 
  };
 }
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

app.listen(3000, () => {
 console.log("El servidor está inicializado en el puerto 3000");
});