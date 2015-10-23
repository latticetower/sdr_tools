package ru.biocad.ig.alascan.constants

import ru.biocad.ig.common.io.pdb.PDBAtomInfo
import ru.biocad.ig.common.structures.aminoacid.SimplifiedAminoAcid
import ru.biocad.ig.common.structures.geometry.GeometryVector
import ru.biocad.ig.common.algorithms.geometry.AminoacidUtils

case class BackboneInfo(val data : Map[String, GeometryVector]) extends AminoacidFragment {

  override def getPDBAtomInfo(aminoacid : SimplifiedAminoAcid,
          x : GeometryVector, y : GeometryVector, z : GeometryVector) : Seq[PDBAtomInfo] = {
      data.map({
        case (k, v) => (k, AminoacidUtils.getGlobalCoordinates(Seq(x, y, z), v.toSeq))
      }).map({case (k, v) => aminoacid.getUpdatedAtomInfo(k, v) }).toSeq
  }
}
