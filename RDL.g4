grammar RDL;

program
	: statement* EOF
	;

statement
	: dataDecl
	| lsmDecl
	| layerDecl
	| readoutDecl
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

readoutDecl
	: READOUT (TORCH)? ID block
	;

connectStmt
	: CONNECT ID ARROW ID (WEIGHT number)?
	;

simulateStmt
	: SIMULATE WITH ID (THROUGH ID)? block
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
	| encodingStmt
	| encodeStepsStmt
	| batchStmt
	;

methodDecl
	: methodName LPAREN paramList? RPAREN block
	;

methodName
	: ID
	| SPIKE
	| READOUT
	;

paramList
	: name (COMMA name)*
	;

name
	: ID
	| WEIGHT
	| SPIKE
	| READOUT
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
	: name ASSIGN expr
	;

augmentedAssignment
	: name op=(PLUS_ASSIGN | MINUS_ASSIGN | MUL_ASSIGN | DIV_ASSIGN) expr
	;

incrementStmt
	: name INCREMENT
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

encodingStmt
	: ENCODING encodingType
	;

encodeStepsStmt
	: ENCODE_STEPS INT
	;

batchStmt
	: BATCH INT
	;

encodingType
	: RATE
	| LATENCY
	| PREENCODED
	| ID
	;

typeValue
	: (ID | READOUT | SPIKE | RATE | LATENCY | PREENCODED | TORCH)+
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
	: name
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
READOUT   : 'readout';
SPIKE     : 'spike';
IF        : 'if';
EMIT      : 'emit';
SIZE      : 'size';
TYPE      : 'type';
STEPS     : 'steps';
THROUGH   : 'through';
ENCODING  : 'encoding';
RATE      : 'rate';
LATENCY   : 'latency';
PREENCODED: 'preencoded';
ENCODE_STEPS : 'encode_steps';
BATCH     : 'batch';

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
