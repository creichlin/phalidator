grammar Phal;

start           : namespace+;

namespace       : identifier  '{' (namespace | norm)* '}';


norm            : identifier ':' booleanExpression;

// BOOLEAN EXPRESSION
booleanExpression: 
                | NOT booleanExpression
                | booleanExpression AND booleanExpression
                | booleanExpression OR booleanExpression
                | booleanValue;
                
booleanValue    : expression LESS expression
                | expression MORE expression
                | expression LESS_EQUAL expression
                | expression MORE_EQUAL expression
                | expression EQUAL expression
                | expression NOT_EQUAL expression
                | expression IN set
                | expression MATCHES pattern
                | '(' booleanExpression ')';
                
// EXPRESSIONS

expression      : primaryExpression
                | expression (MULTIPLICATION | DIVISION) expression
                | expression (ADDITION | SUBTRACTION) expression;

primaryExpression : '(' expression ')'
                  | NUMBER
                  | string
                  | variable
                  ;
                  
pattern           : regexp;
                                      
variable          : DELIMITER                       // .
                  | operation                       // letters
                  | field                           // $title
                  | field DELIMITER operation;      // $title.letters

operation         : LETTERS
                  | LENGTH;

field             : '$'(identifier);

set               : expression RANGE expression
                  | string
                  | array;
                  
string            : QUOTED_STRING;

array             : '[' ((expression ',') * expression)? ']';

regexp            : REGEXP;

identifier        : IDENTIFIER | LETTERS | CHARS | LENGTH | MATCHES | IN | AND | OR;




// NUMBERS
NUMBER            : [+-]? [0-9]+;
                  

// BINARY OPERATIONS
MULTIPLICATION    : '*';
DIVISION          : '/';
ADDITION          : '+';
SUBTRACTION       : '-';

NOT               : '!';
AND               : 'and';
OR                : 'or';
LESS              : '<';
MORE              : '>';
LESS_EQUAL        : '<=';
MORE_EQUAL        : '>=';
EQUAL             : '==';
NOT_EQUAL         : '!=';
IN                : 'in';
RANGE             : '..';
MATCHES           : 'matches';

LETTERS           : 'letters';
CHARS             : 'chars';
LENGTH            : 'length';

DELIMITER         : '.';

QUOTED_STRING     :   '"' ( '\\"' | ~('\n'|'\r') )*? '"';

REGEXP            :   '/' ( '\\/' | ~('\n'|'\r') )*? '/';

IDENTIFIER        : [A-Za-z] ([A-Za-z0-9_])+;

BLOCK_COMMENT     : '/*' .*? '*/' -> skip;
EOL_COMMENT       : '//' ~[\r\n]* -> skip;

WS                : [ \n\t\r]+ -> skip;

