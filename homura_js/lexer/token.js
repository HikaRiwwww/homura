const tokenType = require("./tokenType")

class Token{
    constructor(tokeType, value){
        this._tokenType = tokeType
        this._value = value
    }


    getType(){
        return this._tokenType
    }

    isVariable(){
        return this._tokenType == tokenType.VARIABLE
    }

    isScalar(){
        return this._tokenType == tokenType.STRING ||
        this._tokenType == tokenType.BOOLEAN ||
        this._tokenType == tokenType.FLOAT ||
        this._tokenType == tokenType.INTEGER
    }

    toString(){
        return `type: ${this._tokenType.type}, value: ${this._value}`
    }
}