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
	: methodDecl
	| spikeDecl
	| ifStmt
	| emitStmt
	| assignment
	| augmentedAssignment
	| incrementStmt
	| sizeStmt
	| typeStmt
	| stepsStmt
	;

methodDecl
	: methodName LPAREN paramList? RPAREN block
	;

methodName
	: ID
	| SPIKE
	;

paramList
	: ID (COMMA ID)*
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

augmentedAssignment
	: ID op=(PLUS_ASSIGN | MINUS_ASSIGN | MUL_ASSIGN | DIV_ASSIGN) expr
	;

incrementStmt
	: ID INCREMENT
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
PLUS_ASSIGN : '+=';
MINUS_ASSIGN : '-=';
MUL_ASSIGN : '*=';
DIV_ASSIGN : '/=';
GT        : '>';
LT        : '<';
GTE       : '>=';
LTE       : '<=';
EQ        : '==';
NEQ       : '!=';
PLUS      : '+';
MINUS     : '-';
INCREMENT : '++';
MUL       : '*';
DIV       : '/';
LPAREN    : '(';
RPAREN    : ')';
COMMA     : ',';
LBRACE    : '{';
RBRACE    : '}';

ID        : [a-zA-Z_][a-zA-Z_0-9]*;
INT       : [0-9]+;
FLOAT     : [0-9]+ '.' [0-9]+;
STRING    : '"' (~["\\] | '\\' .)* '"';

LINE_COMMENT : '//' ~[\r\n]* -> skip;
HASH_COMMENT : '#' ~[\r\n]* -> skip;
MINUS_COMMENT : '--' ~[\r\n]* -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;
WS        : [ \t\r\n]+ -> skip;
