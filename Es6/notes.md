# Tool for ES6
Babel, webpack
# Transpiler
- Transpiler reads code written in one language and produces the equivalent code in another
- __babel__ transpiles es6 back into the pre-es6 js.
- Info for [babel js](babeljs.io)
- Using babel
> npm install --save-dev babel-core babel-loader webpack-dev-server babel-preset-es2017 babel-polyfill
# Webpack
- Bundles modules into one.js file.
- Comes with a dev-server
- Starter project with webpack
> npm init -y
> npm install --save-dev webpack

# Coding new ES6 syntax
## Let in es6
- Variables have its scope

## Constants in es6
- Enable protection for variables that must remain the same


# Operating and destructuring
## Spread operator
> let a = [1,2,3];  
> let b = [4, ...a, 10];  
> console.log(b);  
> `b = [4, 1, 2, 3, 10]`

- Spread operator like `concat` operator

> function print(a,b,c) {  
>    console.log(a, b, c);  
> }  
> let z = [1,2,3];  
> print(...z);  
> `result: 1 2 3`

## Destructuring assignment
### Destructuring array
> let c = [100, 200];  
> let [a,b] = c;  
> console.log(a,b);  
> `result: 100 200`  

> let c = [100, 200, 300, 400];  
> let [a, ...b] = c;  
> `a = 100`  
> `b = [200, 300, 400]`

### Destructuring object
> let wizard = {magical: true, power: 10};  
> let {magical, power} = wizard;  
> console.log(magical, power);  
> `result: magical = true, power = 10`

__change variable value with destructuring object technique__  
> let magical = true;  
> let power = 2;  
>   
> let ranger = {magical: false, power: 9};  
> ({magical, power} =  ranger);  
> console.log(magical, power);  
> => `magical = false, power = 9`  

# ES6 functions and methods
## Arrow function
> function(){...} => ()=>{...}

## Arrow function ignoring `this`
- Arrow function directly access to global `this`

## Map method
> let points = [10, 20, 30];  
> points = points.map(element => element + 1);  
> console.log(points); => `[11, 21, 31]`  

## Filter method
> let scores = [90, 50, 60, 40, 70];  
> let passings = scores.filter(element => element >= 50);  
> console.log(passings); => `[90, 50, 60, 70]`

# Module in ES6
For example __calculator.js__ have functions:
> const add(a,b){return a+b};  
> const multiply(x,y){return x*y};  
> export {add, multiply};  
> export default multiply;  

If import calculator file the `multiply` is default function. Some import example:
> import {add , multiply} from './calculator';
> import multiply from './calculator';

__notes:__ we can't export variables with `let`, only variabels with `const` are accepted

# Object oriented programing
