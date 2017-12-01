grammar Query;

orExpr : andExpr (or andExpr)* ;
andExpr : atom (and atom)* ;
atom : in | category | rating | price | name | LParen orExpr RParen ;
or : '||' ;
and : '&&' ;
ineq : gt | gte | lt | lte | eq ;
gt : '>' ;
gte : '>=' ;
lt : '<' ;
lte : '<=' ;
eq : '=' ;
Num : [1-5] ;
in : 'in' LParen string RParen ; // how do I deal with string????
category : 'category' LParen string RParen ;
name : 'name' LParen string RParen ;
rating : 'rating' ineq Num ;
price : 'price' ineq Num ;
LParen : '(' ;
RParen : ')' ;
string : Char* ;
Char : [a-z] | [A-Z] | [0-9] | ' ' | '\'' ;