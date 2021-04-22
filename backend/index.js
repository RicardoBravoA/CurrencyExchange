const express = require("express");
const app = express();

app.listen(3000, () => {
 console.log("The server is running in port 3000");
});

app.get('/api/exchange/:originCurrency/:destinationCurrency', function(req, res) {
    var originCurrency = req.params.originCurrency;
    var destinationCurrency = req.params.destinationCurrency;

    if(originCurrency === destinationCurrency){
        var value;
        switch(originCurrency){
            case "PEN": {
                value = "Soles";
                break;
            }
            case "USD": {
                value = "Dollars";
                break;
            }
            case "EUR": {
                value = "Euros";
                break;
            }
        }
        res.status(200).json({buy:1, sell: 1, multiply: true, buyValue: value, sellValue: value, buyCurrency: originCurrency, sellCurrency: originCurrency});
    }

    //PEN
    if(originCurrency == "PEN" && destinationCurrency == "USD"){
        res.status(200).json({buy:3.698, sell: 3.742, multiply: false, buyValue: "Soles", sellValue: "Dollars", buyCurrency: "PEN", sellCurrency: "USD"});
    }

    if(originCurrency == "PEN" && destinationCurrency == "EUR"){
        res.status(200).json({buy:4.46, sell: 4.5, multiply: false, buyValue: "Soles", sellValue: "Euros", buyCurrency: "PEN", sellCurrency: "EUR"});
    }

    //USD
    if(originCurrency == "USD" && destinationCurrency == "PEN"){
        res.status(200).json({buy:3.698, sell: 3.742, multiply: true, buyValue: "Dollars", sellValue: "Soles", buyCurrency: "USD", sellCurrency: "PEN"});
    }

    if(originCurrency == "USD" && destinationCurrency == "EUR"){
        res.status(200).json({buy:1.2, sell: 1.2, multiply: false, buyValue: "Dollars", sellValue: "Euros", buyCurrency: "USD", sellCurrency: "EUR"});
    }

    //EUR
    if(originCurrency == "EUR" && destinationCurrency == "PEN"){
        res.status(200).json({buy:4.46, sell: 4.5, multiply: true, buyValue: "Euros", sellValue: "Soles", buyCurrency: "EUR", sellCurrency: "PEN"});
    }

    if(originCurrency == "EUR" && destinationCurrency == "USD"){
        res.status(200).json({buy:1.2, sell: 1.2, multiply: true, buyValue: "Euros", sellValue: "Dollars", buyCurrency: "EUR", sellCurrency: "USD"});
    }

    if(originCurrency != "PEN" && originCurrency != "USD" && originCurrency !=  "EUR"){
        res.status(500).json({code:100, message: "Origin currency not found"});
    } else if(destinationCurrency != "PEN" && destinationCurrency != "USD" && destinationCurrency !=  "EUR"){
        res.status(500).json({code:100, message: "Destination currency not found"});
    }

});

app.get('/api/country/:origin', function(req, res) {

    var origin = req.params.origin;
    const flagEurope = "https://cdn.pixabay.com/photo/2013/07/13/01/09/european-union-155207_960_720.png"
    const flagPeru = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/Flag_of_Peru.svg/200px-Flag_of_Peru.svg.png"
    const flagUSA = "https://www.lifeder.com/wp-content/uploads/2018/11/bandera-1795-1818.png"

    if(origin === "PEN"){
        res.status(200).json({countries: 
        [
            {name: "Peru", info: "1 PEN = 1 PEN", image: flagPeru, value: "PEN"}, 
            {name: "United States", info: "1 PEN = 0.27 USD", image: flagUSA, value: "USD"}, 
            {name: "European Union", info: "1 PEN = 0.22 EUR", image: flagEurope, value: "EUR"}
        ]})
    }

    if(origin === "USD"){
        res.status(200).json({countries: 
        [
            {name: "Peru", info: "1 USD = 3.70 PEN", image: flagPeru, value: "PEN"}, 
            {name: "United States", info: "1 USD = 1 USD", image: flagUSA, value: "USD"}, 
            {name: "European Union", info: "1 USD = 0.83 EUR", image: flagEurope, value: "EUR"}
        ]})
    }

    if(origin === "EUR"){
        res.status(200).json({countries: 
        [
            {name: "Peru", info: "1 EUR = 4.46 PEN", image: flagPeru, value: "PEN"}, 
            {name: "United States", info: "1 EUR = 1.21 USD", image: flagUSA, value: "USD"}, 
            {name: "European Union", info: "1 EUR = 1 EUR", image: flagEurope, value: "EUR"}
        ]})
    }

    if(origin != "PEN" && origin != "USD" && origin !=  "EUR"){
        res.status(500).json({code:100, message: "Origin currency not found"});
    }
    
});












