package com.library.librarymanagement.member;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

    @Repository
    public interface MemberRepository extends JpaRepository<MemberEntity,Integer >{

    }

