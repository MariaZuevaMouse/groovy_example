package groovy_dsl_book.initial

def x = 1
switch (x) {
    case 1:
// if x is number 1 we end up here
        break;
    case "mymatch":
// if x equals string "mymatch" we end up here
        break;
//    case /.o./:
//// if x is a string and matches regex /.o./ we end up here
//break;
case ["apple", "orange","pear", 1, 2, 3]:
// if x is found in the list we end up here
        break;
    case [a: 1, b: 2]:
// If x is a key of the map we end up here
        break;
    case 1..5:
// if x is one of the values 1, 2, 3, 4 or 5 we end up here
        break;
}

int n = 0;
while ( n++ < 10) {
    println n
}

for ( m in 0..10)
    println m

for ( it in ["apple", "orange", "pear"])
    println it


def hello = "Hello, World!"
for ( c in hello)
    println c