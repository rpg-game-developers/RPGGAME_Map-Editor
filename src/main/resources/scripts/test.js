var TransformComp = Java.type("com.rpggame.rpggame.component.physics.TransformComp");

function onRender(entity) {
    var trans = entity.getComponent(TransformComp.class);
    var x = trans.getX();
}


