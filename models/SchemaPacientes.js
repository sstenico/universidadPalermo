const mongoose = require('mongoose');
const PacienteSchema = mongoose.Schema ({
    nombre :{
        type : String,
        required : true
    },
    apellido : {
        type: String,
        required : true
    },
    vacuna: {
        type: String
    },
    covid : {
        type: String
    }
});

module.exports = mongoose.model('paciente',PacienteSchema);