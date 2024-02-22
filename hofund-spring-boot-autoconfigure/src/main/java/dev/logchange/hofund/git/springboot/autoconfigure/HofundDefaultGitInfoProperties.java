package dev.logchange.hofund.git.springboot.autoconfigure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Getter
@Setter
@Configuration
@PropertySource(value = "git.properties", ignoreResourceNotFound = true)
@RequiredArgsConstructor
public class HofundDefaultGitInfoProperties {

    private final Environment env;

    public String getCommitId(){
        return get("git.commit.id");
    }

    public String getCommitIdAbbrev(){
        return get("git.commit.id.abbrev");
    }

    public String getDirty(){
        return get("git.dirty");
    }

    public String getBranch(){
        return get("git.branch");
    }

    public String getBuildHost(){
        return get("git.build.host");
    }

    public String getBuildTime(){
        return get("git.build.time");
    }

    private String get(String key){
        String value = env.getProperty(key);
        if (value == null){
            return "";
        }

        return value;
    }
}
