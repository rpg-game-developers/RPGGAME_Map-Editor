var TransformComp = Java.type("com.rpggame.rpggame.component.physics.TransformComp");
var HealthComp = Java.type("com.rpggame.rpggame.component.HealthComp");
var Vector2 = Java.type("com.badlogic.gdx.math.Vector2");

var first = true;

function update(entity) {
	var trans = entity.getComponent(TransformComp.class);
	trans.setX(trans.getX() + 0);
}





