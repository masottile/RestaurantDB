grammar Query;

// Parser rules have to do with the structure
query : andExpr | orExpr | atom ;
orExpr : andExpr (OR andExpr)* ;
andExpr : atom (AND atom)* ;
atom : IN | CATEGORY | RATING | PRICE | NAME | LPAREN orExpr RPAREN ;

IN : 'in' TEXT;
CATEGORY : 'category' TEXT;
NAME : 'name' TEXT;
RATING : 'rating' WS? INEQ WS? NUM ;
PRICE : 'price' WS? INEQ WS? NUM ;

// Lexer rules have to to with making tokens
LPAREN : '(' ;
RPAREN : ')' ;
NUM : [1-5] ;
TEXT : LPAREN ([a-z] | [A-Z] | [0-9] | ' ' | '\'')+ RPAREN ;
OR : '||' ;
AND : '&&' ;
INEQ : GT | GTE | LT | LTE | EQ ;
GT : '>' ;
GTE : '>=' ;
LT : '<' ;
LTE : '<=' ;
EQ : '=' ;

// skips all extra whitespace
WS : (' ' | '\t'| '\n' | '\r')+ {skip();} ;