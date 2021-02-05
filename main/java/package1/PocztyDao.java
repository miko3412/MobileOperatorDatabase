package package1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PocztyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Konstruktor z jdbcTemplate
     * @param jdbcTemplate instancja jdbcTemplate
     */
    public PocztyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Poczta> list(){
        String sql="SELECT * FROM POCZTY";
        List<Poczta> listPoczty = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Poczta.class));
        return listPoczty;
    }

    /* (C)reate */
    public void save(Poczta poczta){
    	SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
    	insertActor.withTableName("poczty").usingColumns("kod_poczty","poczta");
    	
    	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(poczta);
    	insertActor.execute(param);
    }

    /* (R)ead */
    public Poczta get(int nr_poczty){
        Object[] args = {nr_poczty};
        String sql = "SELECT * FROM POCZTY WHERE NR_POCZTY = "+args[0];
        Poczta poczta = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Poczta.class));
        return poczta;
    }
    /* (U)pdate */
    public void update(Poczta poczta){
    	String sql = "UPDATE POCZTY SET kod_poczty=:kod_poczty, poczta=:poczta WHERE nr_poczty=:nr_poczty";
    	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(poczta);
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    	
    	template.update(sql, param);
    }
    /* (D)elete */
    public void delete(int id){
    	String sql = "DELETE FROM POCZTY WHERE NR_POCZTY = ?";
    	jdbcTemplate.update(sql,id);
    }
}
