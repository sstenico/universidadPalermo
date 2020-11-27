const express = require("express");
const router = express.Router();
const PostP = require ('../models/SchemaPacientes');

router.get('/', async (req, res) => {
    try {
       const getpaciente = await PostP.find();
       res.json(getpaciente);
    }catch(err){
        res.json({message: err});
    }
});

/* busco un paciente especifico */
router.get('/id/:pacienteid', async (req, res) => {
    try {
       const getpacienteid = await PostP.findById(req.params.pacienteid);
       res.json(getpacienteid);
    }catch(err){
        res.json({message: err});
    }
});

//busco por querystring
router.get('/q', async (req, res) => {
    try {
        console.log(req.query.nombre);
        console.log(req.query.covid);
        let respuesta = 'no hay informacion para los valores ingresados. ';
        const getpacientequery = await PostP.find({nombre: req.query.nombre, covid: req.query.covid});
        console.log(getpacientequery.length);

        if (getpacientequery.length == 0 )
            res.json(respuesta +'nombre: ' + req.query.nombre +  ' - covid: ' + req.query.covid);
        else
            res.json(getpacientequery);
    }catch(err){
        res.json({message: err});
    }
});


router.post('/', async (req, res) => {
    const postpaciente = new PostP ({
        nombre : req.body.nombre,
        apellido: req.body.apellido,
        vacuna: req.body.vacuna,
        covid: req.body.covid
    });  
  
    try{
        const guardarpaciente = await postpaciente.save();
        res.json(guardarpaciente);
    }catch(err){
        res.json({message: err});
    }
});

//elimino un paciente
router.delete ('/:pacienteid', async (req, res) => {
    try {
       const deletepacienteid = await PostP.remove({_id: req.params.pacienteid});
       res.json(deletepacienteid);
    }catch(err){
        res.json({message: err});
    }
});

router.put ('/:pacienteid', async (req, res) => {
    try {
       const updatepacienteid = await PostP.updateOne(
                {_id: req.params.pacienteid},
                {$set: {covid: req.body.covid, vacuna: req.body.vacuna}}
                );
       res.json(updatepacienteid);
    }catch(err){
        res.json({message: err});
    }
});

module.exports = router;