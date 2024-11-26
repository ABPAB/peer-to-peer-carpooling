const mongoose = require('mongoose');

const locationSchema = new mongoose.Schema({
    userId: { type: String, required: true },
    coordinates: {
        type: { type: String, enum: ['Point'], required: true },
        coordinates: { type: [Number], required: true } 
    },
    timestamp: { type: Date, default: Date.now },
    ipAddress: { type: String, required: true }
});

locationSchema.index({ coordinates: '2dsphere' });

module.exports = mongoose.model('Location', locationSchema);
