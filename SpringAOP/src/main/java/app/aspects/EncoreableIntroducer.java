package app.aspects;

import app.beans.DefaultEncoreable;
import app.beans.Encoreable;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EncoreableIntroducer {

	@DeclareParents(value="app.beans.Performance+", defaultImpl=DefaultEncoreable.class)
	public static Encoreable encoreable;
}
