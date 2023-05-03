
def getFactorial = {number -> number <= 1 ? 1 : number * call(number-1)}

test = getFactorial(7);
println test

//Access values outside of closure

def greeting = "Goodbye";

def sayGoodbye = { theName -> println("$greeting $theName")}
sayGoodbye("Derek")

//List operations
def numList = [1,2,3,4];
numList.each{println(it)};

def employees = [
        'Paul' : 34,
        'Sally': 35,
        "Sam" : 36
];

employees.each {println("$it.key : $it.value")}

def randomNums = [1,2,3,4,5]
randomNums.each{num -> if(num%2 == 0) println(num)}
