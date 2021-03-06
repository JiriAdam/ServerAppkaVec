package util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Irrielde on 25.3.2015.
 */
public class ApplicationContextProvider  implements ApplicationContextAware {

        private static ApplicationContext context;

        public ApplicationContext getApplicationContext() {
            return context;
        }

        @Override
        public void setApplicationContext(ApplicationContext ac)
                throws BeansException {
            context = ac;
        }

    }

