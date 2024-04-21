package e4i.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, e4i.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, e4i.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, e4i.domain.User.class.getName());
            createCache(cm, e4i.domain.Authority.class.getName());
            createCache(cm, e4i.domain.User.class.getName() + ".authorities");
            createCache(cm, e4i.domain.PortalUser.class.getName());
            createCache(cm, e4i.domain.PortalUser.class.getName() + ".managedOrganizations");
            createCache(cm, e4i.domain.PortalUser.class.getName() + ".contactOrganizations");
            createCache(cm, e4i.domain.PortalUser.class.getName() + ".managedInfrastructures");
            createCache(cm, e4i.domain.PortalUser.class.getName() + ".contactInfrastructures");
            createCache(cm, e4i.domain.PortalUser.class.getName() + ".requests");
            createCache(cm, e4i.domain.PortalUser.class.getName() + ".calendars");
            createCache(cm, e4i.domain.PortalUser.class.getName() + ".riProposals");
            createCache(cm, e4i.domain.PortalUser.class.getName() + ".serviceProposals");
            createCache(cm, e4i.domain.PortalUser.class.getName() + ".portalUserRoles");
            createCache(cm, e4i.domain.PortalUserRole.class.getName());
            createCache(cm, e4i.domain.PortalUserRole.class.getName() + ".users");
            createCache(cm, e4i.domain.PortalUserOrganization.class.getName());
            createCache(cm, e4i.domain.PortalUserOrganization.class.getName() + ".portalUsers");
            createCache(cm, e4i.domain.PortalUserOrganization.class.getName() + ".requests");
            createCache(cm, e4i.domain.PuoLegalStatus.class.getName());
            createCache(cm, e4i.domain.RiResearchOrganization.class.getName());
            createCache(cm, e4i.domain.RiResearchOrganization.class.getName() + ".infrastructures");
            createCache(cm, e4i.domain.RiResearchOrganization.class.getName() + ".subdomains");
            createCache(cm, e4i.domain.RipoActivityStatus.class.getName());
            createCache(cm, e4i.domain.ScientificDomain.class.getName());
            createCache(cm, e4i.domain.ScientificDomain.class.getName() + ".subdomains");
            createCache(cm, e4i.domain.ScientificSubdomain.class.getName());
            createCache(cm, e4i.domain.ScientificSubdomain.class.getName() + ".organizations");
            createCache(cm, e4i.domain.ScientificSubdomain.class.getName() + ".infrastructures");
            createCache(cm, e4i.domain.ScientificSubdomain.class.getName() + ".services");
            createCache(cm, e4i.domain.ResearchInfrastructure.class.getName());
            createCache(cm, e4i.domain.ResearchInfrastructure.class.getName() + ".services");
            createCache(cm, e4i.domain.ResearchInfrastructure.class.getName() + ".calendars");
            createCache(cm, e4i.domain.ResearchInfrastructure.class.getName() + ".subdomains");
            createCache(cm, e4i.domain.ResearchInfrastructure.class.getName() + ".subcategories");
            createCache(cm, e4i.domain.RiStatus.class.getName());
            createCache(cm, e4i.domain.RiCategory.class.getName());
            createCache(cm, e4i.domain.RiSubcategory.class.getName());
            createCache(cm, e4i.domain.RiSubcategory.class.getName() + ".infrastructures");
            createCache(cm, e4i.domain.RiAccessType.class.getName());
            createCache(cm, e4i.domain.RiAccessMode.class.getName());
            createCache(cm, e4i.domain.RiService.class.getName());
            createCache(cm, e4i.domain.RiService.class.getName() + ".requests");
            createCache(cm, e4i.domain.RiService.class.getName() + ".calendars");
            createCache(cm, e4i.domain.RiService.class.getName() + ".subdomains");
            createCache(cm, e4i.domain.ServiceType.class.getName());
            createCache(cm, e4i.domain.ServiceSubtype.class.getName());
            createCache(cm, e4i.domain.ServiceAccreditation.class.getName());
            createCache(cm, e4i.domain.ServiceAvailability.class.getName());
            createCache(cm, e4i.domain.RequestForService.class.getName());
            createCache(cm, e4i.domain.RequestRespond.class.getName());
            createCache(cm, e4i.domain.RequestRespond.class.getName() + ".respondsToOffers");
            createCache(cm, e4i.domain.RequestAnswer.class.getName());
            createCache(cm, e4i.domain.ServiceOfferRespond.class.getName());
            createCache(cm, e4i.domain.AnswerToOffer.class.getName());
            createCache(cm, e4i.domain.RiServiceStatus.class.getName());
            createCache(cm, e4i.domain.RiProcurementProposal.class.getName());
            createCache(cm, e4i.domain.ServiceProposal.class.getName());
            createCache(cm, e4i.domain.Researcher.class.getName());
            createCache(cm, e4i.domain.Researcher.class.getName() + ".institutions");
            createCache(cm, e4i.domain.ResearchInstitution.class.getName());
            createCache(cm, e4i.domain.ResearchInstitution.class.getName() + ".researchers");
            createCache(cm, e4i.domain.RiCalendar.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
