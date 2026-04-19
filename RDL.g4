grammar RDL;

program
	: statement* EOF
	;

statement
	: dataDecl
	| lsmDecl
	| layerDecl
	| connectStmt
	| simulateStmt
	| saveStmt
	;

dataDecl
	: DATA ID ASSIGN LOAD CSV STRING
	;

lsmDecl
	: LSM ID block
	;

layerDecl
	: LAYER (TORCH)? ID block
	;

connectStmt
	: CONNECT ID ARROW ID (WEIGHT number)?
	;

simulateStmt
	: SIMULATE WITH ID block
	;

saveStmt
	: SAVE ID
	;

block
	: LBRACE blockStatement* RBRACE
	;

blockStatement
	: spikeDecl
	| ifStmt
	| emitStmt
	| assignment
	| sizeStmt
	| typeStmt
	| stepsStmt
	;

spikeDecl
	: SPIKE LPAREN RPAREN block
	;

ifStmt
	: IF expr block
	;

emitStmt
	: EMIT SPIKE
	;

assignment
	: ID ASSIGN expr
	;

sizeStmt
	: SIZE INT
	;

typeStmt
	: TYPE typeValue
	;

stepsStmt
	: STEPS INT
	;

typeValue
	: ID+
	;

expr
	: comparisonExpr
	;

comparisonExpr
	: additiveExpr ((GT | LT | GTE | LTE | EQ | NEQ) additiveExpr)?
	;

additiveExpr
	: multiplicativeExpr ((PLUS | MINUS) multiplicativeExpr)*
	;

multiplicativeExpr
	: unaryExpr ((MUL | DIV) unaryExpr)*
	;

unaryExpr
	: MINUS unaryExpr
	| primary
	;

primary
	: ID
	| number
	| STRING
	| LPAREN expr RPAREN
	;

number
	: INT
	| FLOAT
	;

DATA      : 'data';
LOAD      : 'load';
CSV       : 'csv';
LSM       : 'LSM';
LAYER     : 'layer';
TORCH     : 'torch';
CONNECT   : 'connect';
WEIGHT    : 'weight';
SIMULATE  : 'simulate';
WITH      : 'with';
SAVE      : 'save';
SPIKE     : 'spike';
IF        : 'if';
EMIT      : 'emit';
SIZE      : 'size';
TYPE      : 'type';
STEPS     : 'steps';

ARROW     : '->';
ASSIGN    : '=';
GT        : '>';
LT        : '<';
GTE       : '>=';
LTE       : '<=';
EQ        : '==';
NEQ       : '!=';
PLUS      : '+';
MINUS     : '-';
MUL       : '*';
DIV       : '/';
LPAREN    : '(';
RPAREN    : ')';
LBRACE    : '{';
RBRACE    : '}';

ID        : [a-zA-Z_][a-zA-Z_0-9]*;
INT       : [0-9]+;
FLOAT     : [0-9]+ '.' [0-9]+;
STRING    : '"' (~["\\] | '\\' .)* '"';

LINE_COMMENT : '//' ~[\r\n]* -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;
WS        : [ \t\r\n]+ -> skip;
