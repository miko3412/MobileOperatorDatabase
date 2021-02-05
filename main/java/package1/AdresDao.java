package package1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AdresDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Konstruktor z jdbcTemplate
     * @param jdbcTemplate instancja jdbcTemplate
     */
    public AdresDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Adres> list(){
        String sql="SELECT * FROM ADRESY";
        List<Adres> listAdresy = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Adres.class));
        return listAdresy;
    }
    
    /* (C)reate */
    public void save(Adres adres){
    	SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
    	insertActor.withTableName("adresy").usingColumns("miasto","ulica","nr_lokalu","nr_poczty");
    	
    	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(adres);
    	insertActor.execute(param);
    }
    
    /* (R)ead */
    public Adres get(int nr_adresu){
        Object[] args = {nr_adresu};
        String sql = "SELECT * FROM ADRESY WHERE NR_ADRESU = "+args[0];
        Adres adres = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Adres.class));
        return adres;
    }
    /* (U)pdate */
    public void update(Adres adres){
    	String sql = "UPDATE ADRESY SET miasto=:miasto, ulica=:ulica, nr_lokalu=:nr_lokalu, nr_poczty=:nr_poczty WHERE nr_adresu=:nr_adresu";
    	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(adres);
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    	
    	template.update(sql, param);
    }
    /* (D)elete */
    public void delete(int id){
    	String sql = "DELETE FROM ADRESY WHERE NR_ADRESU = ?";
    	jdbcTemplate.update(sql,id);
    }
}
