
; Some common constants you might want to use
Include "consts.bb"

Graphics3D 800,600,32,0
SetBuffer BackBuffer()

; Environment setup
light = CreateLight()

plane=CreatePlane()
grass_tex=LoadTexture("grass.bmp")
EntityTexture plane,grass_tex

Type Camera
Field cam
Field x#, y#, z#
Field pivot
End Type

c.Camera = New Camera
c\cam = CreateCamera()
c\pivot = CreatePivot()
c\x# = 0
c\y# = 5
c\z# = 0
PositionEntity c\cam, c\x#, c\y#, c\z#

c1 = CreateCone()
c2 = CreateCone()
c3 = CreateCone()
c4 = CreateCone()

PositionEntity c1, 0, 2, 5
PositionEntity c2, 5, 2, 0
PositionEntity c3, 0, 2, -5
PositionEntity c4, -5, 2, 0

; Main "character"
Type Player
Field mesh
Field x#, y#, z#
End Type

p.Player = New Player
p\mesh = CreateCube()
p\x# = 0
p\y# = 1
p\z# = 5
PositionEntity p\mesh, p\x#, p\y#, p\z#

; Game loop
While Not KeyDown(KEY_ESC)

	;;;
	PositionEntity c\cam, c\x#, p\y# + 5, c\z#
	
	dist# = Distance#(c\x#, c\z#, p\x#, p\z#)
	adjustedDist# = dist# - 10
	If (adjustedDist# > 0) Then
		newX# = c\x# + forwardX#
		newZ# = c\z# + forwardZ#
		c\x# = Lerp(c\x#, newX#, 0.2)
		c\z# = Lerp(c\z#, newZ#, 0.2)
	ElseIf (adjustedDist# < -2) Then
		newX# = c\x# - forwardX#
		newZ# = c\z# - forwardZ#
		c\x# = Lerp(c\x#, newX#, 0.2)
		c\z# = Lerp(c\z#, newZ#, 0.2)
	EndIf
	
	; Transform the forward vector (0,0,1) from camera's local space to world space
	TFormVector 0,0,1, c\cam, 0
	forwardX# = TFormedX()
	forwardY# = TFormedY()
	forwardZ# = TFormedZ()
	
	; Transform the right vector (1,0,0) from camera's local space to world space
	TFormVector 1,0,0, c\cam, 0
	rightX# = TFormedX()
	rightY# = TFormedY()
	rightZ# = TFormedZ()

	; Vertical
	If KeyDown(UP_A)=True Then
		p\x#=p\x#+(moveSpeed# * forwardX#)
		p\z#=p\z#+(moveSpeed# * forwardZ#)
	ElseIf KeyDown(DOWN_A)=True And dist# > 2 Then
		p\x#=p\x#-(moveSpeed# * forwardX#)
		p\z#=p\z#-(moveSpeed# * forwardZ#)
	EndIf
	
	; Horizontal
	If KeyDown(LEFT_A)=True Then
		p\x#=p\x#-(moveSpeed# * rightX#)
		p\z#=p\z#-(moveSpeed# * rightZ#)
	ElseIf KeyDown(RIGHT_A)=True Then
		p\x#=p\x#+(moveSpeed# * rightX#)
		p\z#=p\z#+(moveSpeed# * rightZ#)
	EndIf
	
	PositionEntity p\mesh, p\x#, p\y#, p\z#
	PositionEntity c\pivot, p\x#, p\y# + 3, p\z#
	PointEntity c\cam, c\pivot
	
	; 3D Rendering
	RenderWorld
	
	; 2D Rendering
	Text 0,0,"Cube Position: " + p\x# + " x " + p\z#
	Text 0,16,":x" + forwardX# + ", :z" + forwardZ# + " &&& :x" + rightX# + " - :z" + rightZ#
	Text 0,32,"Distance: " + dist#
	Flip
Wend
End