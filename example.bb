
; Some common constants you might want to use
Include "consts.bb"

Graphics3D 800,600,32,SCREEN_WINDOWED
SetBuffer BackBuffer()

; Environment setup
camera = CreateCamera()
PositionEntity camera,0,5,0
light = CreateLight()

plane=CreatePlane()
grass_tex=LoadTexture("grass.jpg")
EntityTexture plane,grass_tex

; Main "character"
cube = CreateCube()
cubX#=0
cubY#=1
cubZ#=0
PositionEntity cube,cubX#,cubY#,cubZ#

; Game loop
While Not 1=2 ;KeyDown(KEY_ESC)

	; Vertical
	If KeyDown(UP_A)=True Then
		cubZ#=cubZ#-moveSpeed#
	ElseIf KeyDown(DOWN_A)=True Then
		cubZ#=cubZ#+moveSpeed#
	EndIf
	
	; Horizontal
	If KeyDown(LEFT_A)=True Then
		cubX#=cubX#-moveSpeed#
	ElseIf KeyDown(RIGHT_A)=True Then
		cubX#=cubX#+moveSpeed#
	EndIf
	
	PositionEntity cube, cubX#, cubY#, cubZ#
    PointEntity camera, cube
	
	; 3D Rendering
	RenderWorld
	
	; 2D Rendering
	Text 0,0,"Cube Position: " + cubX + " x " + cubZ
	Flip
Wend