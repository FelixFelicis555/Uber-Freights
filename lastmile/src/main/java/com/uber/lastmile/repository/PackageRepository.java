
package com.uber.lastmile.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uber.lastmile.model.db.DAOPackage;

@Repository
public interface PackageRepository extends JpaRepository<DAOPackage, Long> {

    DAOPackage findByPackageId(Long packageId);
    
}