var TransformComp = Java.type("com.rpggame.rpggame.component.physics.TransformComp");
var Vector2 = Java.type("com.badlogic.gdx.math.Vector2");

function onRender(entity) {
    var trans = entity.getComponent(TransformComp.class);
    var x = trans.getRotation();
    trans.setRotation(x + 0.1);
    trans.setScale(new Vector2(2, 1));
}



