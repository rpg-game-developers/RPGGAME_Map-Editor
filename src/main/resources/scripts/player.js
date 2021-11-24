var TransformComp = Java.type("com.rpggame.rpggame.component.physics.TransformComp");
var HealthComp = Java.type("com.rpggame.rpggame.component.HealthComp");
var Vector2 = Java.type("com.badlogic.gdx.math.Vector2");
var EntityObserver = Java.type("com.rpggame.rpggame.entity.EntityObserver");
var DeathEvent = Java.type("com.rpggame.rpggame.event.DeathEvent");

var first = true;

function update(entity) {
	if (first) {
		var observer = new EntityObserver(DeathEvent.class) {
			onNotify: function(event) {
				observer.entity.getComponent(TransformComp.class)
					.setPosition(new Vector2(0.0, 0.0));
				observer.entity.getComponent(HealthComp.class)
					.resetHealth();
			}
		}
		entity.subscribe(entity, observer);
		first = false;
	}
	
	entity.getComponent(HealthComp.class).damage(entity, 1);
}


