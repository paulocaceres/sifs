package com.sifs

import org.apache.commons.lang.builder.HashCodeBuilder

class SifsUserSifsRole implements Serializable {

	SifsUser sifsUser
	SifsRole sifsRole

	boolean equals(other) {
		if (!(other instanceof SifsUserSifsRole)) {
			return false
		}

		other.sifsUser?.id == sifsUser?.id &&
			other.sifsRole?.id == sifsRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (sifsUser) builder.append(sifsUser.id)
		if (sifsRole) builder.append(sifsRole.id)
		builder.toHashCode()
	}

	static SifsUserSifsRole get(long sifsUserId, long sifsRoleId) {
		find 'from SifsUserSifsRole where sifsUser.id=:sifsUserId and sifsRole.id=:sifsRoleId',
			[sifsUserId: sifsUserId, sifsRoleId: sifsRoleId]
	}

	static SifsUserSifsRole create(SifsUser sifsUser, SifsRole sifsRole, boolean flush = false) {
		new SifsUserSifsRole(sifsUser: sifsUser, sifsRole: sifsRole).save(flush: flush, insert: true)
	}

	static boolean remove(SifsUser sifsUser, SifsRole sifsRole, boolean flush = false) {
		SifsUserSifsRole instance = SifsUserSifsRole.findBySifsUserAndSifsRole(sifsUser, sifsRole)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(SifsUser sifsUser) {
		executeUpdate 'DELETE FROM SifsUserSifsRole WHERE sifsUser=:sifsUser', [sifsUser: sifsUser]
	}

	static void removeAll(SifsRole sifsRole) {
		executeUpdate 'DELETE FROM SifsUserSifsRole WHERE sifsRole=:sifsRole', [sifsRole: sifsRole]
	}

	static mapping = {
		id composite: ['sifsRole', 'sifsUser']
		version false
	}
}
