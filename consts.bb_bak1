
Const SCREEN_WINDOWED=0
Const SCREEN_FULL=1

; Controls
Const LEFT_A=30
Const RIGHT_A=32
Const UP_A=17
Const DOWN_A=31

; Keyboard shortcuts
Const KEY_ESC=1

Const moveSpeed#=0.2

Function Lerp#(a#, b#, t#)
	If (t# < 0) Then t# = 0
	If (t# > 1) Then t# = 1
	Return a# + t# * (b# - a#)
End Function

Function Distance#(x1#, z1#, x2#, z2#)
	dx# = x2# - x1#
	dz# = z2# - z1#
	Return Sqr(dx# * dx# + dz# * dz#)
End Function