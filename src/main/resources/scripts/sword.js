var TransformComp = Java.type("com.rpggame.rpggame.component.physics.TransformComp");
var Vector2 = Java.type("com.badlogic.gdx.math.Vector2");
var Interpolation = Java.type("com.badlogic.gdx.math.Interpolation");
var Gdx = Java.type("com.badlogic.gdx.Gdx");
var Input = Java.type("com.badlogic.gdx.Input");
var Math = Java.type("java.lang.Math");

var easRotation = Interpolation.swing;
var elapsed = 0.0;
var lifeTime = 0.3;

function update(entity) {
	transform = entity.getComponent(TransformComp.class);
	if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
		elapsed += 0.016;
		var progress = Math.min(1.0, elapsed/lifeTime);
		transform.setRotation(-easRotation.apply(progress));
		if (elapsed > lifeTime) {
			elapsed -= lifeTime;
		}
	} else {
		elapsed = 0.0;
		transform.setRotation(0.0);
	}
}



