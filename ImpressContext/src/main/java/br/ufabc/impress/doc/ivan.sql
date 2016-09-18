UPDATE fusion SET fusion_name = 'LIGHT FUSION' , fusion_text = 'select max ( cast(DemoEsperType.replication, int) ) as maxReplication, max(bornDate) as maxBornDate, DemoEsperType.resourceLogValue as value from DemoEsperType().win:length_batch(1) where DemoEsperType.resource.resourceType.id = 1'
	WHERE id_fusion = 1;

UPDATE fusion SET fusion_name = 'AIR FUSION' , fusion_text = 'select max ( cast(DemoEsperType.replication, int) ) as maxReplication, max(bornDate) as maxBornDate, DemoEsperType.resourceLogValue as value from DemoEsperType().win:length_batch(1) where DemoEsperType.resource.resourceType.id = 2'
	WHERE id_fusion = 2;

UPDATE fusion SET fusion_name = 'TRAFFIC FUSION' , fusion_text = 'select max ( cast(DemoEsperType.replication, int) ) as maxReplication, max(bornDate) as maxBornDate, DemoEsperType.resourceLogValue as value from DemoEsperType().win:length_batch(1) where DemoEsperType.resource.resourceType.id = 3'
	WHERE id_fusion = 3;

UPDATE fusion SET fusion_name = 'NOISE FUSION' , fusion_text = 'select max ( cast(DemoEsperType.replication, int) ) as maxReplication, max(bornDate) as maxBornDate, DemoEsperType.resourceLogValue as value from DemoEsperType().win:length_batch(1) where DemoEsperType.resource.resourceType.id = 4'
	WHERE id_fusion = 4;

UPDATE fusion SET fusion_name = 'STRUCTURAL FUSION' , fusion_text = 'select max ( cast(DemoEsperType.replication, int) ) as maxReplication, max(bornDate) as maxBornDate, DemoEsperType.resourceLogValue as value from DemoEsperType().win:length_batch(1) where DemoEsperType.resource.resourceType.id = 5'
	WHERE id_fusion = 5;

UPDATE fusion SET fusion_name = 'WASTE FUSION' , fusion_text = 'select max ( cast(DemoEsperType.replication, int) ) as maxReplication, max(bornDate) as maxBornDate, DemoEsperType.resourceLogValue as value from DemoEsperType().win:length_batch(1) where DemoEsperType.resource.resourceType.id = 6'
	WHERE id_fusion = 6;