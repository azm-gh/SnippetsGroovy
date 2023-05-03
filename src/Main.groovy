
printClosure = { println "Hello World" }
printClosure.call()

argClosure = {name -> println "Hello ${name}"}
argClosure.call("Jakub");

argbrackClosure = {name -> println "Hello $name"}
argbrackClosure.call("Test");

// We cannot do direct referencing in the method
// But its possible with closures
def str = "Kubula"

myClosure3 = {println "Hello $str "}
myClosure3.call();

//We can also pass closures as variables in a method

def myMethod(closure) {
  closure.call("Groovy")
}
myMethod(myClosure3)

// Closures can have return type
def myClosure4 = {
  a,b,c ->
    return (a+b+c)
}
test = myClosure4(1,2,3)
println test

def myList1 = ["Apples", "Orange"];
println myList1.each {it}

def myMap1 = [
        'subject':'groovy',
        'topic':'closures'
        ]

println myMap1.each {it}

def myList = [1,2,3,4,5]
println myList.find{item -> item == 8}
myList.findAll{item -> item > 3}
println myList.any{item -> item > 7}
println myList.every{ item -> item > 3}
println myList.collect {item -> item*2 }





