

// https://www.youtube.com/watch?v=URkFOLywex4

def list1 = [1,2,3,4,5]
def list2 = [20,30,40,50,60]

def factor = 2
def printIt =  { e -> println e * factor }
list1.each printIt

//last param is special -> I can avoid the brackeets
def foo(value, closure) {
    closure(value * 2)
}
//Passing closures different way
foo(3, {println it})
foo(3) {println it}
foo 3, {println it}

// def greet = { name -> println "Hello $name"}
// greet('Joe')

//Delegate - an object which attaches to a closure.
// A bit like prototypal inheritance in javascript
// -> object reaches back in a chain.
// If closure does not understand something it will forward it.


//here we can change the behaviour without modifying the closure, by changing the delegate outside
// Inheritance is very static.Prototypal inheritance is very dynamic -> can change the delegate and changing the code
// you are working with.

def greet2 = { name -> println "${toUpperCase()} $name"}
def salutations = ['Hello', 'Hi']
for(salutation in salutations) {
    greet2.delegate = salutation //delegate is string therefore it understands toUpperCase()
    greet2('Joe')
}

//Imagine this is Java code
//Noisy code - lots of mailers

public class Mailer {
    def to(String adr) {
        println('to');
   }
    def from(String adr) {
        println('from');
    }
    def sub(String line) {
        println('sub');
    }
    def body(String msg) {
        println('body');
    }
    public static void send(closure) {
        Mailer mailer = new Mailer();
        mailer.with closure //makes a copy does not change it directly
        println("Sending..")
    }
}

Mailer.send {
    to("jj@jj.cz");
    from("bulder@jj.cz");
    sub('Your code is awesome');
    body("...");
}

//Currying - lambda calculus, category theory, Haskell Curry

def log = { level, date, msg  ->
    println "$level $date $msg"
}
// A bit noisy and duplication
def today = new Date()
//log 'Warning', today, 'Starting..'
//log 'Warning', today, 'running...'

def logWarningToday = log.curry('warning', today) //currying first 2 params
logWarningToday 'starting'
logWarningToday 'running'

//currying from from right to left - same direction of params as with
def logTodayStarting = log.rcurry(today, 'Starting')
logTodayStarting('warning');

//ncurry - dont use it, takes random params
// ---

def printInfo(closure) {
    println '---'
    println closure.maximumNumberOfParameters
    println closure.parameterTypes

}

printInfo { e -> println e}

def langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy', 'Ruby': 'Matz']

langs.each{entry -> println entry}
langs.each{k,v -> println "$k --- $v" }

//Thread methods

// Standard java version
Thread th = new Thread(new Runnable() {
    public void run() {
        println "in another thread"
    }
})
th.start()
println "In main Thread"

Thread.start {
    println "in another thread"
}
//classic iteration
def factorial(number) {
    def result = 1
    for(i in 1..number) {
        result *= i
    }
    result
}

println factorial(5)

//recursion
def factorial2(number ) {
    if(number == 1)
        1
    else
        number * factorial(number - 1)
}
//SICP - recommended book. Structure and interpretation computer programing
// procedure vs. process
// TCO - tail call optimization - optimize (eg. haskell,erlang,closure,scala does great TCO)

//Blows up
def foo(n) {
    if(n == 1)
        throw new RuntimeException('blowup')
    else
        n * foo(n - 1)
}
//quietly turns into interation instead of recursion
def foo2 = { n ->
    if(n == 1)
        throw new RuntimeException('blowup')
    else
       foo2.trampoline(n - 1)
}.trampoline()

//lets rewrite factorial - trampoline is TCO purely written in closures
def factorial2
factorial2 = { fact, n ->
    if( n==1 )
        fact
    else
        factorial2.trampoline(fact * n, n - 1)
}.trampoline()

println factorial2(1,5)

//MEMOIZATION - used in dynamic programming

//slower
def fib(n) {
    if(n == 1 || n == 2)
        1
    else
        fib(n-1) + fib(n-2)
}

// 1 1 2 3 5 8 13 26

println fib(5)

// memoize version - function must be pure
def fib2
fib2 = { n ->
    if(n == 1 || n == 2)
        1
    else
        fib(n - 1) + fib(n - 2)
}.memoize()

// But you are using your memory, but if you running out of memory, you can use different aspects(buffer)




